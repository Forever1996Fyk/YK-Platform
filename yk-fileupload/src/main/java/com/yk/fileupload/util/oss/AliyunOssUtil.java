package com.yk.fileupload.util.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.yk.common.entity.BaseAttachment;
import com.yk.common.entity.Bucket;
import com.yk.common.enums.CannedAccessControlEnum;
import com.yk.common.enums.RedundancyEnum;
import com.yk.common.enums.StorageClassEnum;
import com.yk.common.exception.file.OssException;
import com.yk.common.util.StringUtils;
import com.yk.common.util.TimeUtils;
import com.yk.fileupload.config.properties.AliyunOssProperties;
import com.yk.fileupload.model.pojo.DocAttachment;
import com.yk.fileupload.model.pojo.ImageAttachment;
import com.yk.fileupload.model.pojo.VideoAttachment;
import com.yk.fileupload.util.AttachmentUtils;
import com.yk.framework.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;

/**
 * @ClassName AliyunOssUtil
 * @Description 阿里云Oss工具类
 * @Author YuKai Fan
 * @Date 2020/6/15 20:43
 * @Version 1.0
 **/
@Slf4j
public class AliyunOssUtil {
    /**
     * 基础配置
     */
    private final static String ACCESS_KEY_ID = AliyunOssProperties.getInstance().getAccessKeyId();
    private final static String ACCESS_KEY_SECRET = AliyunOssProperties.getInstance().getAccessKeySecret();
    private final static String ENDPOINT = AliyunOssProperties.getInstance().getEndpoint();
    private final static String BUCKET_NAME = AliyunOssProperties.getInstance().getBucketName();

    private static OSS init() {
        return new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }

    private static OSS createBucket(Bucket bucket) {
        // 判断名称是否已存在
        OSS client = init();
        if (!client.doesBucketExist(BUCKET_NAME)) {
            // 创建CreateBucketRequest对象
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(BUCKET_NAME);
            createBucketRequest.setStorageClass(StorageClassEnum.getStorageClassByType(bucket.getStorageType()));
            createBucketRequest.setDataRedundancyType(RedundancyEnum.getDataRedundancyTypeByType(bucket.getDataRedundancyType()));
            createBucketRequest.setCannedACL(CannedAccessControlEnum.getCannedAccessControlListByType(bucket.getCannedACL()));
            //创建存储空间
            client.createBucket(createBucketRequest);
        }

        return client;
    }

    /**
     * 简单上传文件
     * @param is
     * @return
     */
    public static String simpleUpload(InputStream is, Bucket bucket, String filePath) {
        try {
            log.info("开始上传oss...");
            OSS client = createBucket(bucket);
            //上传文件byte数组
            client.putObject(BUCKET_NAME, filePath, is);
            client.shutdown();

            String uploadCallbackUrl = getUploadCallbackUrl(bucket, filePath);
            if (StringUtils.isBlank(uploadCallbackUrl)){
                throw new OssException("上传失败, 返回链接为空");
            }
            log.info("上传成功到oss, 链接 == [{}]", uploadCallbackUrl);
            return uploadCallbackUrl;
        } catch (OSSException e) {
            log.error("上传失败, error = {}", e.getMessage());
            throw new OssException(StringUtils.format("上传失败, [{}]", e.getMessage()));
        } catch (ClientException e) {
            log.error("上传失败, error = {}", e.getMessage());
            throw new OssException(StringUtils.format("上传失败, [{}]", e.getMessage()));
        }
    }

    /**
     * 下载oss文件
     * @param attachment
     * @throws IOException
     */
    public static ResponseEntity downloadOssObject(BaseAttachment attachment) throws IOException {
        OSS client = init();
        OSSObject ossObject = client.getObject(new GetObjectRequest(BUCKET_NAME, attachment.getAttachPath()));
        InputStream objectContent = ossObject.getObjectContent();
        try {
            // file inputstream
            InputStreamResource inputStreamResource = new InputStreamResource(objectContent);
            // 设置附件名
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(attachment.getAttachOriginTitle(), "UTF-8"));
            return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new OssException(StringUtils.format("OSS文件下载失败, [{}]", e.getMessage()));
        } finally {
            if (objectContent == null) {
                objectContent.close();
                client.shutdown();
            }
        }
    }

    /**
     * 获取图片附件对象
     * @param file
     * @return
     */
    public static ImageAttachment getImageAttachment(MultipartFile file, String ownerId, String attachAttr, Bucket bucket) throws IOException {
        ImageAttachment attachment = AttachmentUtils.getImageAttachment(file);
        attachment.setAttachPath(AttachmentUtils.genOssFilePath(attachment.getAttachSuffix(), attachment.getAttachName()));
        attachment.setCreateTime(TimeUtils.getCurrentDatetime());
        attachment.setUpdateTime(TimeUtils.getCurrentDatetime());
        attachment.setCreateUserId(ShiroUtils.getCurrentUserId());
        attachment.setUpdateUserId(ShiroUtils.getCurrentUserId());
        attachment.setAttachAttr(attachAttr);
        attachment.setOwnerId(ownerId);
        attachment.setAttachUrl(simpleUpload(file.getInputStream(), bucket, attachment.getAttachPath()));
        return attachment;
    }

    /**
     * 获取视频附件对象
     * @param file
     * @return
     */
    public static VideoAttachment getVideoAttachment(MultipartFile file, String ownerId, String attachAttr, Bucket bucket) throws IOException {
        VideoAttachment attachment = AttachmentUtils.getVideoAttachment(file);
        attachment.setAttachPath(AttachmentUtils.genOssFilePath(attachment.getAttachSuffix(), attachment.getAttachName()));
        attachment.setCreateTime(TimeUtils.getCurrentDatetime());
        attachment.setUpdateTime(TimeUtils.getCurrentDatetime());
        attachment.setCreateUserId(ShiroUtils.getCurrentUserId());
        attachment.setUpdateUserId(ShiroUtils.getCurrentUserId());
        attachment.setAttachAttr(attachAttr);
        attachment.setOwnerId(ownerId);
        attachment.setAttachUrl(simpleUpload(file.getInputStream(), bucket, attachment.getAttachPath()));
        return attachment;
    }

    /**
     * 获取文档附件对象
     * @param file
     * @return
     */
    public static DocAttachment getDocAttachment(MultipartFile file, String ownerId, String attachAttr, Bucket bucket) throws IOException {
        DocAttachment attachment = AttachmentUtils.getDocAttachment(file);
        attachment.setAttachPath(AttachmentUtils.genOssFilePath(attachment.getAttachSuffix(), attachment.getAttachName()));
        attachment.setCreateTime(TimeUtils.getCurrentDatetime());
        attachment.setUpdateTime(TimeUtils.getCurrentDatetime());
        attachment.setCreateUserId(ShiroUtils.getCurrentUserId());
        attachment.setUpdateUserId(ShiroUtils.getCurrentUserId());
        attachment.setAttachAttr(attachAttr);
        attachment.setOwnerId(ownerId);
        attachment.setAttachUrl(simpleUpload(file.getInputStream(), bucket, attachment.getAttachPath()));
        return attachment;
    }

    /**
     * 上传后获取访问url地址
     * @param bucket
     * @param filePath
     * @return
     */
    private static String getUploadCallbackUrl(Bucket bucket, String filePath) {
        String url = "";
        if (CannedAccessControlEnum.ACL_READ_WRITE.getType().equals(bucket.getCannedACL()) || CannedAccessControlEnum.ACL_READ.getType().equals(bucket.getCannedACL())) {
            url = "http://" + BUCKET_NAME + "." + ENDPOINT + "/" + filePath;
        } else {
            //如果访问权限为私有, 则需要签名访问
            url = "http://" + BUCKET_NAME + "." + ENDPOINT + "/" + filePath + "?xxxx";
        }
        return url;
    }
}

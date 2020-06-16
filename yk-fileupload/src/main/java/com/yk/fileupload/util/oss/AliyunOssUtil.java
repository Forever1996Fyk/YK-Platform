package com.yk.fileupload.util.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CreateBucketRequest;
import com.yk.common.entity.Bucket;
import com.yk.common.enums.CannedAccessControlEnum;
import com.yk.common.enums.RedundancyEnum;
import com.yk.common.enums.StorageClassEnum;
import com.yk.common.exception.file.OssException;
import com.yk.common.util.FileUtils;
import com.yk.common.util.StringUtils;
import com.yk.common.util.TimeUtils;
import com.yk.fileupload.config.properties.AliyunOssProperties;
import com.yk.fileupload.model.pojo.ImageAttachment;
import com.yk.fileupload.model.pojo.VideoAttachment;
import com.yk.fileupload.util.AttachmentUtils;
import com.yk.framework.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

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

    private static OSS client = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

    private static void createBucket(Bucket bucket) {
        // 判断名称是否已存在
        if (!client.doesBucketExist(bucket.getBucketName())) {
            // 创建CreateBucketRequest对象
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucket.getBucketName());
            createBucketRequest.setStorageClass(StorageClassEnum.getStorageClassByType(bucket.getStorageType()));
            createBucketRequest.setDataRedundancyType(RedundancyEnum.getDataRedundancyTypeByType(bucket.getDataRedundancyType()));
            createBucketRequest.setCannedACL(CannedAccessControlEnum.getCannedAccessControlListByType(bucket.getCannedACL()));
            //创建存储空间
            client.createBucket(createBucketRequest);
        }
    }

    /**
     * 简单上传文件
     * @param is
     * @return
     */
    public static String simpleUpload(InputStream is, Bucket bucket, String filePath) {
        try {
            log.info("开始上传oss...");
            createBucket(bucket);
            //上传文件byte数组
            client.putObject(bucket.getBucketName(), filePath, is);
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
     * 获取图片附件对象
     * @param file
     * @return
     */
    public static ImageAttachment getImageAttachment(MultipartFile file, String ownerId, String attachAttr, Bucket bucket) throws IOException {
        ImageAttachment attachment = AttachmentUtils.getImageAttachment(file);
        attachment.setAttachPath(AttachmentUtils.genFilePath(attachment.getAttachSuffix(), attachment.getAttachName()));
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
        attachment.setAttachPath(AttachmentUtils.genFilePath(attachment.getAttachSuffix(), attachment.getAttachName()));
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
        if (CannedAccessControlEnum.ACL_READ_WRITE.getType().equals(bucket.getCannedACL())
                || CannedAccessControlEnum.ACL_READ.equals(bucket.getCannedACL())) {
            url = "http://" + bucket.getBucketName() + "." + ENDPOINT + "/" + filePath;
        } else {
            //如果访问权限为私有, 则需要签名访问
            url = "http://" + bucket.getBucketName() + "." + ENDPOINT + "/" + filePath + "?xxxx";
        }
        return url;
    }
}

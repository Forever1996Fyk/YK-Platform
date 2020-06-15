package com.yk.fileupload.util.fastdfs;

import com.google.common.collect.Lists;
import com.yk.common.entity.BaseAttachment;
import com.yk.common.enums.ContentTypeEnum;
import com.yk.common.exception.file.FastDfsException;
import com.yk.common.util.SpringUtils;
import com.yk.common.util.StringUtils;
import com.yk.common.util.TimeUtils;
import com.yk.fileupload.config.properties.FileProperties;
import com.yk.fileupload.manager.TrackerServerPool;
import com.yk.fileupload.model.pojo.ImageAttachment;
import com.yk.fileupload.model.pojo.VideoAttachment;
import com.yk.fileupload.util.AttachmentUtils;
import com.yk.framework.util.ShiroUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @ClassName FastDfsAttachmentUtils
 * @Description fastDFS上传工具类
 * @Author YuKai Fan
 * @Date 2020/6/13 22:08
 * @Version 1.0
 **/
public class FastDfsAttachmentUtils {
    private static final Logger logger = LoggerFactory.getLogger(FastDfsAttachmentUtils.class);

    private static FileProperties fileProperties = SpringUtils.getBean(FileProperties.class);

    /**
     * 获取图片附件对象
     * @param file
     * @return
     */
    public static ImageAttachment getImageAttachment(MultipartFile file, String ownerId, String attachAttr) throws IOException {
        ImageAttachment attachment = AttachmentUtils.getImageAttachment(file);
        attachment.setCreateTime(TimeUtils.getCurrentDatetime());
        attachment.setUpdateTime(TimeUtils.getCurrentDatetime());
        attachment.setCreateUserId(ShiroUtils.getCurrentUserId());
        attachment.setUpdateUserId(ShiroUtils.getCurrentUserId());
        attachment.setAttachAttr(attachAttr);
        attachment.setOwnerId(ownerId);
        attachment.setAttachPath(upload(file, attachment));
        attachment.setAttachUrl(fileProperties.getFastDFSAddr() + attachment.getAttachPath());
        return attachment;

    }

    /**
     * 获取视频附件对象
     * @param file
     * @return
     */
    public static VideoAttachment getVideoAttachment(MultipartFile file, String ownerId, String attachAttr) throws IOException {
        VideoAttachment attachment = AttachmentUtils.getVideoAttachment(file);
        attachment.setCreateTime(TimeUtils.getCurrentDatetime());
        attachment.setUpdateTime(TimeUtils.getCurrentDatetime());
        attachment.setCreateUserId(ShiroUtils.getCurrentUserId());
        attachment.setUpdateUserId(ShiroUtils.getCurrentUserId());
        attachment.setAttachAttr(attachAttr);
        attachment.setOwnerId(ownerId);
        attachment.setAttachPath(upload(file, attachment));
        attachment.setAttachUrl(fileProperties.getFastDFSAddr() + attachment.getAttachPath());
        return attachment;

    }

    /**
     * 使用MultipartFile 上传
     *
     * @author YuKai Fan
     * @param file
     * @param attachment
     * @return java.lang.String
     * @date 2020/6/13 22:29
     */
    public static String upload(MultipartFile file, BaseAttachment attachment) {
        if (file == null || file.isEmpty()) {
            throw new FastDfsException("上传文件为空!");
        }

        String path;
        try {
            path = upload(file.getInputStream(), attachment);

        } catch (IOException e) {
            e.printStackTrace();
            throw new FastDfsException("FastDFS 文件上传失败");
        }
        return path;
    }

    /**
     * 通用上传方法
     *
     * @author YuKai Fan
     * @param is 文件输入流
     * @param attachment 文件信息
     * @return java.lang.String 组名+附件路径，如：group1/M00/00/00/xxxxxxxxxxxxxx.jpg
     * @date 2020/6/13 22:27
     */
    private static String upload(InputStream is, BaseAttachment attachment) {
        String path;
        TrackerServer trackerServer = TrackerServerPool.getTrackerServer();
        StorageClient1 storageClient = new StorageClient1(trackerServer, null);
        // 文件描述
        NameValuePair[] nvps = null;
        List<NameValuePair> nvpsList = Lists.newArrayList();
        nvpsList.add(new NameValuePair("fileName", attachment.getAttachOriginTitle()));

        if (!CollectionUtils.isEmpty(nvpsList)) {
            nvps = new NameValuePair[nvpsList.size()];
            nvpsList.toArray(nvps);
        }
        try {
            // 读取流
            byte[] buff = new byte[is.available()];
            is.read(buff, 0, buff.length);

            //上传fastDFS
            path = storageClient.upload_file1(buff, attachment.getAttachSuffix(), nvps);

            if (StringUtils.isBlank(path)) {
                throw new FastDfsException("FastDFS 文件上传失败");
            }

            if (logger.isDebugEnabled()) {
                logger.debug("upload file success 文件上传成功, return path is 返回路径为 [{}] ", path);
            }
        } catch (IOException | MyException e) {
            e.printStackTrace();
            throw new FastDfsException("FastDFS 文件上传失败");
        } finally {
            // 关闭流
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 返还对象
        TrackerServerPool.returnObject(trackerServer);
        return path;
    }

    public static ResponseEntity<byte[]> downloadFastDfsAttachment(HttpServletResponse response, BaseAttachment attachment) {
        return download(attachment, null, response);
    }

    public static ResponseEntity<byte[]> download(BaseAttachment attachment, OutputStream os, HttpServletResponse response) {
        if (StringUtils.isBlank(attachment.getAttachPath())) {
            throw new FastDfsException("FastDfs文件地址错误!");
        }
        // 文件名
        String fileName = attachment.getAttachOriginTitle();
        if (StringUtils.isBlank(fileName)) {
            fileName = "未命名文件";
        }

        String contentType = ContentTypeEnum.getContentTypeBySuffix(attachment.getAttachSuffix());
        logger.info("下载文件, 文件路径 = {}, 文件名称 = {}", attachment.getAttachPath(), fileName);

        TrackerServer trackerServer = TrackerServerPool.getTrackerServer();
        StorageClient1 storageClient = new StorageClient1(trackerServer, null);
        try {
            // 下载
            byte[] bytes = storageClient.download_file1(attachment.getAttachPath());

            if (bytes == null) {
                bytes = "暂未找到具体文件".getBytes();
                int index = fileName.indexOf('.');
                if (index > 0) {
                    fileName = fileName.substring(0, index);
                }
                fileName += ".zip";
                fileName = "未上传文件" + fileName;
            }
            // 设置附件名
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, "UTF-8"));

            // 返还对象
            TrackerServerPool.returnObject(trackerServer);

            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);

        } catch (IOException | MyException e) {
            e.printStackTrace();
            throw new FastDfsException("FastDfs文件下载失败");
        }
    }
}

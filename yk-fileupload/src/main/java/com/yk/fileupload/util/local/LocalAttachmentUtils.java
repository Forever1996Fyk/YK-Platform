package com.yk.fileupload.util.local;

import com.yk.common.entity.BaseAttachment;
import com.yk.common.exception.file.FileReadErrorException;
import com.yk.common.util.TimeUtils;
import com.yk.fileupload.model.pojo.DocAttachment;
import com.yk.fileupload.model.pojo.ImageAttachment;
import com.yk.fileupload.model.pojo.VideoAttachment;
import com.yk.fileupload.util.AttachmentUtils;
import com.yk.framework.util.ShiroUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @program: YK-Platform
 * @description: 本地文件上传
 * @author: YuKai Fan
 * @create: 2020-06-10 21:32
 **/
public class LocalAttachmentUtils {
    private static final Logger logger = LoggerFactory.getLogger(LocalAttachmentUtils.class);

    /**
     * 获取图片附件对象
     * @param file
     * @return
     */
    public static ImageAttachment getImageAttachment(MultipartFile file, String ownerId, String attachAttr) throws IOException {
        ImageAttachment attachment = AttachmentUtils.getImageAttachment(file);
        attachment.setAttachPath(AttachmentUtils.genFilePath(attachment.getAttachSuffix(), attachment.getAttachName()));
        attachment.setCreateTime(TimeUtils.getCurrentDatetime());
        attachment.setUpdateTime(TimeUtils.getCurrentDatetime());
        attachment.setCreateUserId(ShiroUtils.getCurrentUserId());
        attachment.setUpdateUserId(ShiroUtils.getCurrentUserId());
        attachment.setAttachAttr(attachAttr);
        attachment.setOwnerId(ownerId);
        return attachment;
    }

    /**
     * 获取视频附件对象
     * @param file
     * @return
     */
    public static VideoAttachment getVideoAttachment(MultipartFile file, String ownerId, String attachAttr) throws IOException {
        VideoAttachment attachment = AttachmentUtils.getVideoAttachment(file);
        attachment.setAttachPath(AttachmentUtils.genFilePath(attachment.getAttachSuffix(), attachment.getAttachName()));
        attachment.setCreateTime(TimeUtils.getCurrentDatetime());
        attachment.setUpdateTime(TimeUtils.getCurrentDatetime());
        attachment.setCreateUserId(ShiroUtils.getCurrentUserId());
        attachment.setUpdateUserId(ShiroUtils.getCurrentUserId());
        attachment.setAttachAttr(attachAttr);
        attachment.setOwnerId(ownerId);
        return attachment;
    }

    /**
     * 获取本地文档附件对象
     * @param file
     * @param ownerId
     * @param attachAttr
     * @return
     */
    public static DocAttachment getDocAttachment(MultipartFile file, String ownerId, String attachAttr) throws IOException {
        DocAttachment attachment = AttachmentUtils.getDocAttachment(file);
        attachment.setAttachPath(AttachmentUtils.genFilePath(attachment.getAttachSuffix(), attachment.getAttachName()));
        attachment.setCreateTime(TimeUtils.getCurrentDatetime());
        attachment.setUpdateTime(TimeUtils.getCurrentDatetime());
        attachment.setCreateUserId(ShiroUtils.getCurrentUserId());
        attachment.setUpdateUserId(ShiroUtils.getCurrentUserId());
        attachment.setAttachAttr(attachAttr);
        attachment.setOwnerId(ownerId);
        return attachment;
    }
    /**
     * 获取文件字节流
     * @param attachPath
     * @return
     */
    public static byte[] getLocalImage(String attachPath) {
        try {
            File file = new File(attachPath);
            if (file.exists() && file.isFile()) {
                return org.apache.commons.io.FileUtils.readFileToByteArray(file);
            }
        } catch (IOException e) {
            logger.error("文件读取异常 [{}], 异常信息为 [{}]", e, e.getMessage());
            throw new FileReadErrorException();
        }

        return null;
    }

    /**
     * 下载本地附件
     * @param request
     * @param attachment
     */
    public static ResponseEntity<byte[]> downloadLocalAttachment(HttpServletRequest request, BaseAttachment attachment) throws IOException {
        byte[] bytes = getLocalImage(attachment.getAttachPath());
        String fileName = attachment.getAttachOriginTitle();
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

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    public static void previewLocalDoc(HttpServletResponse response, DocAttachment attachment) {
        File file = new File(attachment.getAttachPath());
        try {
            OutputStream os = response.getOutputStream();
            FileInputStream fis = new FileInputStream(file);
            IOUtils.copy(fis, os);

            fis.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
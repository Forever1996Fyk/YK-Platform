package com.yk.fileupload.util.local;

import com.yk.common.exception.file.FileReadErrorException;
import com.yk.common.util.FileUtils;
import com.yk.common.util.TimeUtils;
import com.yk.fileupload.manager.factory.AsyncFactory;
import com.yk.fileupload.model.pojo.ImageAttachment;
import com.yk.fileupload.model.pojo.VideoAttachment;
import com.yk.fileupload.util.AttachmentUtils;
import com.yk.framework.util.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
    public static synchronized ImageAttachment getImageAttachment(MultipartFile file, String ownerId, String attachAttr) throws IOException {
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
    public static synchronized VideoAttachment getVideoAttachment(MultipartFile file, String ownerId, String attachAttr) throws IOException {
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
}
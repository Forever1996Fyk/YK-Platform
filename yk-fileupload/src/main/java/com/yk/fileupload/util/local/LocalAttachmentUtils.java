package com.yk.fileupload.util.local;

import com.yk.fileupload.model.pojo.ImageAttachment;
import com.yk.fileupload.util.AttachmentUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: YK-Platform
 * @description: 本地文件上传
 * @author: YuKai Fan
 * @create: 2020-06-10 21:32
 **/
public class LocalAttachmentUtils {

    /**
     * 获取本地文件路径
     * @param file
     * @return
     */
    public static ImageAttachment getImageAttachment(MultipartFile file) {
        ImageAttachment attachment = AttachmentUtils.getImageAttachment(file);
        attachment.setAttachPath(AttachmentUtils.genFilePath(attachment.getAttachSuffix(), attachment.getAttachName()));
        return attachment;
    }
}
package com.yk.fileupload.manager.factory;

import com.yk.common.entity.BaseAttachment;
import com.yk.common.util.FileUtils;
import com.yk.common.util.SpringUtils;
import com.yk.common.util.TimeUtils;
import com.yk.fileupload.model.pojo.ImageAttachment;
import com.yk.fileupload.service.ImageAttachmentService;
import com.yk.fileupload.util.local.LocalAttachmentUtils;
import com.yk.framework.util.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: YK-Platform
 * @description: 异步任务工厂
 * @author: YuKai Fan
 * @create: 2020-06-11 10:38
 **/
public class AsyncFactory {
    private static final Logger logger = LoggerFactory.getLogger(AsyncFactory.class);

    /**
     * 异步上传文件任务
     * @param file
     * @return
     */
    public static Runnable asyncLocalImageFileUpload(final MultipartFile file) {
        return () -> {
            ImageAttachment attachment = LocalAttachmentUtils.getImageAttachment(file);
            FileUtils.transferTo(attachment.getAttachPath());

            attachment.setCreateTime(TimeUtils.getCurrentDatetime());
            attachment.setUpdateTime(TimeUtils.getCurrentDatetime());
            attachment.setCreateUserId(ShiroUtils.getCurrentUserId());
            attachment.setUpdateUserId(ShiroUtils.getCurrentUserId());
            ImageAttachmentService imageAttachmentService = SpringUtils.getBean(ImageAttachmentService.class);
            imageAttachmentService.insertImageAttachment(attachment);
        };
    }
}
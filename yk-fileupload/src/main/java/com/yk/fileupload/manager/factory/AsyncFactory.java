package com.yk.fileupload.manager.factory;

import com.yk.common.util.FileUtils;
import com.yk.common.util.TimeUtils;
import com.yk.fileupload.model.pojo.ImageAttachment;
import com.yk.framework.util.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * @program: YK-Platform
 * @description: 异步任务工厂
 * @author: YuKai Fan
 * @create: 2020-06-11 10:38
 **/
public class AsyncFactory {
    private static final Logger logger = LoggerFactory.getLogger(AsyncFactory.class);

    /**
     * 异步上传 本地服务文件任务
     * @author YuKai Fan
     * @param is 文件流
     * @param attachment
     * @return java.lang.Runnable
     * @date 2020/6/11 22:17
     */
    public static Runnable asyncLocalImageFileUpload(final InputStream is, final ImageAttachment attachment) {
        return () -> {
            FileUtils.transferTo(is, attachment.getAttachPath());

            attachment.setCreateTime(TimeUtils.getCurrentDatetime());
            attachment.setUpdateTime(TimeUtils.getCurrentDatetime());
            attachment.setCreateUserId(ShiroUtils.getCurrentUserId());
            attachment.setUpdateUserId(ShiroUtils.getCurrentUserId());
            attachment.setAttachSha1(FileUtils.getFileSHA1(is));
            attachment.setAttachMd5(FileUtils.getFileMD5(is));
//            ImageAttachmentService imageAttachmentService = SpringUtils.getBean(ImageAttachmentService.class);
//            imageAttachmentService.insertImageAttachment(attachment);
        };
    }
}
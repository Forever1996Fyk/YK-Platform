package com.yk.fileupload.util;

import com.yk.common.entity.BaseAttachment;
import com.yk.common.exception.file.FileSizeException;
import com.yk.common.util.AppUtils;
import com.yk.common.util.FileUtils;
import com.yk.common.util.SpringUtils;
import com.yk.common.util.TimeUtils;
import com.yk.fileupload.config.properties.FileProperties;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;

/**
 * @program: YK-Platform
 * @description: 附件工具类
 * @author: YuKai Fan
 * @create: 2020-06-10 20:35
 **/
public class AttachmentUtils {

    /**
     * 获取基础附件信息
     * @param file
     * @return
     */
    public static BaseAttachment getBaseAttachment(MultipartFile file) {
        if (file.getSize() == 0) {
            throw new FileSizeException(0L);
        }
        BaseAttachment attachment = new BaseAttachment();
        attachment.setId(AppUtils.randomId());
        attachment.setAttachSize(file.getSize());
        attachment.setStatus(1);
        attachment.setAttachOriginTitle(file.getOriginalFilename());
        attachment.setAttachSuffix(FileUtils.getFileSuffix(file.getOriginalFilename()));
        attachment.setAttachName(FileUtils.genFileName(attachment.getId(), attachment.getAttachSuffix()));
        attachment.setAttachSha1(FileUtils.getFileSHA1(file));
        attachment.setAttachMd5(FileUtils.getFileMD5(file));
        return attachment;
    }

    /**
     * 生成文件所在路径
     * @param suffix
     * @param attachName
     * @return
     */
    public static String genFilePath(String suffix, String attachName) {
        return getResourcesPath() + File.separator + getModulePath(suffix) + File.separator + genDateMkdir() + File.separator + attachName;
    }

    /**
     * 根据后缀名 判断文件类型, 获取对应的模块路径
     * @param suffix
     * @return
     */
    private static String getModulePath(String suffix) {
        return FileUtils.getModulePath(FileUtils.getTypeFlag(FileUtils.getType(suffix)));
    }

    /**
     * 获取文件根路径
     * @return
     */
    private static String getResourcesPath() {
        FileProperties fileProperties = SpringUtils.getBean(FileProperties.class);
        return fileProperties.getFilePath();
    }

    /**
     * 获取日期文件夹目录
     * @return
     */
    private static String genDateMkdir() {
        return "/" + TimeUtils.parseTime(LocalDateTime.now(), TimeUtils.TimeFormat.SHORT_DATE_PATTERN_NONE) + "/";
    }
}
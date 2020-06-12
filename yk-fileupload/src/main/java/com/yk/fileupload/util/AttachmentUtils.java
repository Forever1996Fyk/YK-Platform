package com.yk.fileupload.util;

import com.yk.common.exception.file.FileSizeException;
import com.yk.common.util.AppUtils;
import com.yk.common.util.FileUtils;
import com.yk.common.util.SpringUtils;
import com.yk.common.util.TimeUtils;
import com.yk.fileupload.config.properties.FileProperties;
import com.yk.fileupload.model.pojo.DocAttachment;
import com.yk.fileupload.model.pojo.ImageAttachment;
import com.yk.fileupload.model.pojo.VideoAttachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @program: YK-Platform
 * @description: 附件工具类
 * @author: YuKai Fan
 * @create: 2020-06-10 20:35
 **/
public class AttachmentUtils {

    /**
     * 获取图片附件信息
     * @param file
     * @return
     */
    public static ImageAttachment getImageAttachment(MultipartFile file) throws IOException {
        if (file.getInputStream().available() == 0) {
            throw new FileSizeException(0L);
        }
        ImageAttachment attachment = new ImageAttachment();
        attachment.setId(AppUtils.randomId());
        attachment.setAttachSize((long) file.getInputStream().available());
        attachment.setStatus(1);
        attachment.setAttachOriginTitle(file.getOriginalFilename());
        attachment.setAttachSuffix(FileUtils.getFileSuffix(file.getOriginalFilename()));
        attachment.setAttachName(FileUtils.genFileName(attachment.getId(), attachment.getAttachSuffix()));
        attachment.setAttachSha1(FileUtils.getFileSHA1(file.getInputStream()));
        attachment.setAttachMd5(FileUtils.getFileMD5(file.getInputStream()));
        return attachment;
    }

    /**
     * 获取视频附件信息
     * @param file
     * @return
     */
    public static VideoAttachment getVideoAttachment(MultipartFile file) throws IOException {
        if (file.getInputStream().available() == 0) {
            throw new FileSizeException(0L);
        }
        VideoAttachment attachment = new VideoAttachment();
        attachment.setId(AppUtils.randomId());
        attachment.setAttachSize((long) file.getInputStream().available());
        attachment.setStatus(1);
        attachment.setAttachOriginTitle(file.getOriginalFilename());
        attachment.setAttachSuffix(FileUtils.getFileSuffix(file.getOriginalFilename()));
        attachment.setAttachName(FileUtils.genFileName(attachment.getId(), attachment.getAttachSuffix()));
        attachment.setAttachSha1(FileUtils.getFileSHA1(file.getInputStream()));
        attachment.setAttachMd5(FileUtils.getFileMD5(file.getInputStream()));
        return attachment;
    }

    /**
     * 获取文档附件信息
     * @param file
     * @return
     */
    public static DocAttachment getDocAttachment(MultipartFile file) throws IOException {
        if (file.getInputStream().available() == 0) {
            throw new FileSizeException(0L);
        }
        DocAttachment attachment = new DocAttachment();
        attachment.setId(AppUtils.randomId());
        attachment.setAttachSize((long) file.getInputStream().available());
        attachment.setStatus(1);
        attachment.setAttachOriginTitle(file.getOriginalFilename());
        attachment.setAttachSuffix(FileUtils.getFileSuffix(file.getOriginalFilename()));
        attachment.setAttachName(FileUtils.genFileName(attachment.getId(), attachment.getAttachSuffix()));
        attachment.setAttachSha1(FileUtils.getFileSHA1(file.getInputStream()));
        attachment.setAttachMd5(FileUtils.getFileMD5(file.getInputStream()));
        return attachment;
    }

    /**
     * 生成文件所在路径
     * @param suffix
     * @param attachName
     * @return
     */
    public static String genFilePath(String suffix, String attachName) {
        return getResourcesPath() + getModulePath(suffix) + genDateMkdir() + attachName;
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
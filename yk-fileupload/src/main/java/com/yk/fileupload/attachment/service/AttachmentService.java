package com.yk.fileupload.attachment.service;

import com.yk.common.entity.BaseAttachment;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: YK-Platform
 * @description: 附件通用接口
 * @author: YuKai Fan
 * @create: 2020-06-10 22:10
 **/
public interface AttachmentService<T extends BaseAttachment> {

    /**
     * 上传附件
     * @param request
     * @return
     */
    T uploadAttachment(HttpServletRequest request);

    /**
     * 上传附件
     * @param request
     * @param ownerId
     * @return
     */
    T uploadAttachment(HttpServletRequest request, String ownerId);

    /**
     * 根据上传方式上传附件
     * @param request
     * @param ownerId
     * @param uploadType local:本地 fastdfs: 文件服务器 oss: 阿里云OSS服务
     * @return
     */
    T uploadAttachment(HttpServletRequest request, String ownerId, String uploadType);

    /**
     * 批量上传附件
     * @param request
     * @return
     */
    Integer uploadBatchAttachment(HttpServletRequest request);

    /**
     * 批量上传附件
     * @param request
     * @param ownerId
     * @return
     */
    Boolean uploadBatchAttachment(HttpServletRequest request, String ownerId);

    /**
     * 根据上传方式批量上传附件
     * @param request
     * @param ownerId
     * @param uploadType local:本地 fastdfs: 文件服务器 oss: 阿里云OSS服务
     * @return
     */
    Boolean uploadBatchAttachment(HttpServletRequest request, String ownerId, String uploadType);

    /**
     * 附件下载
     */
    void downloadAttachment();
}
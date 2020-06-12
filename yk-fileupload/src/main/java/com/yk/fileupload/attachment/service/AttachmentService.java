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
     * 本地服务 上传附件
     * @param request
     * @param ownerId
     * @return
     */
    T uploadLocalAttachment(HttpServletRequest request, String ownerId);

    /**
     * fastDFS服务 上传附件
     * @param request
     * @param ownerId
     * @return
     */
    T uploadFastDFSAttachment(HttpServletRequest request, String ownerId);

    /**
     * OSS服务 上传附件
     * @param request
     * @param ownerId
     * @return
     */
    T uploadOSSAttachment(HttpServletRequest request, String ownerId);

    /**
     * 本地服务 批量上传附件
     * @param request
     * @param ownerId
     * @return
     */
    int uploadLocalBatchAttachment(HttpServletRequest request, String ownerId);

    /**
     * fastDFS服务 批量上传附件
     * @param request
     * @param ownerId
     * @return
     */
    int uploadFastDFSBatchAttachment(HttpServletRequest request, String ownerId);

    /**
     * OSS服务 批量上传附件
     * @param request
     * @param ownerId
     * @return
     */
    int uploadOSSBatchAttachment(HttpServletRequest request, String ownerId);


    /**
     * 附件下载
     */
    void downloadAttachment();
}
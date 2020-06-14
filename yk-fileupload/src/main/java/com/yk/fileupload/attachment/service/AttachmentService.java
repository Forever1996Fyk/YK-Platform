package com.yk.fileupload.attachment.service;

import com.yk.common.entity.BaseAttachment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    T uploadLocalAttachment(HttpServletRequest request, String ownerId, String attachAttr) throws IOException;

    /**
     * fastDFS服务 上传附件
     * @param request
     * @param ownerId
     * @return
     */
    T uploadFastDFSAttachment(HttpServletRequest request, String ownerId, String attachAttr) throws IOException;

    /**
     * OSS服务 上传附件
     * @param request
     * @param ownerId
     * @return
     */
    T uploadOSSAttachment(HttpServletRequest request, String ownerId, String attachAttr);

    /**
     * 本地服务 批量上传附件
     * @param request
     * @param ownerId
     * @return
     */
    int uploadLocalBatchAttachment(HttpServletRequest request, String ownerId, String attachAttr);

    /**
     * fastDFS服务 批量上传附件
     * @param request
     * @param ownerId
     * @return
     */
    int uploadFastDFSBatchAttachment(HttpServletRequest request, String ownerId, String attachAttr);

    /**
     * OSS服务 批量上传附件
     * @param request
     * @param ownerId
     * @return
     */
    int uploadOSSBatchAttachment(HttpServletRequest request, String ownerId, String attachAttr);


    /**
     * 本地附件下载
     * @author YuKai Fan
     * @param response
     * @param attId
     * @return void
     * @date 2020/6/14 18:59
     */
    void downloadLocalAttachment(HttpServletResponse response, String attId);

    /**
     * fastDFS附件下载
     * @author YuKai Fan
     * @param response
     * @param attId
     * @return void
     * @date 2020/6/14 18:59
     */
    void downloadFastDfsAttachment(HttpServletResponse response, String attId);

    /**
     * OSS附件下载
     * @author YuKai Fan
     * @param response
     * @param attId
     * @return void
     * @date 2020/6/14 18:59
     */
    void downloadOSSAttachment(HttpServletResponse response, String attId);
}
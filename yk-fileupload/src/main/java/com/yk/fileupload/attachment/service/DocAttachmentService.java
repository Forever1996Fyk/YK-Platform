package com.yk.fileupload.attachment.service;

import com.yk.fileupload.model.pojo.DocAttachment;
import com.yk.fileupload.model.query.DocAttachmentQuery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program: YK-Platform
 * @description: 文档接口service
 * @author: YuKai Fan
 * @create: 2020-06-17 14:23
 **/
public interface DocAttachmentService extends AttachmentService<DocAttachment> {

    /**
     * 获取文档附件列表(分页)
     * @param start
     * @param pageSize
     * @param docAttachmentQuery
     * @return
     */
    List<DocAttachment> listDocAttachments(int start, int pageSize, DocAttachmentQuery docAttachmentQuery);

    /**
     * 获取文档附件列表
     * @param docAttachmentQuery
     * @return
     */
    List<DocAttachment> listDocAttachments(DocAttachmentQuery docAttachmentQuery);

    /**
     * 删除文档附件
     * @param id
     * @return
     */
    int deleteDocAttachmentById(String id);

    /**
     * 批量删除文档附件
     * @param ids
     * @return
     */
    int deleteBatchDocAttachmentByIds(List<String> ids);

    /**
     * 预览本地文档
     * @param request
     * @param response
     * @param attId
     */
    void previewLocalDoc(HttpServletRequest request, HttpServletResponse response, String attId) throws IOException;

    /**
     * 预览FastDfs文档
     * @param request
     * @param response
     * @param attId
     */
    void previewFastDfsDoc(HttpServletRequest request, HttpServletResponse response, String attId);

    /**
     * 预览Oss文档
     * @param request
     * @param response
     * @param attId
     */
    void previewOssDoc(HttpServletRequest request, HttpServletResponse response, String attId);
}
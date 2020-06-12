package com.yk.fileupload.attachment.service;

import com.yk.fileupload.model.pojo.ImageAttachment;
import com.yk.fileupload.model.query.ImageAttachmentQuery;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: YK-Platform
 * @description: 图片附件操作接口
 * @author: YuKai Fan
 * @create: 2020-06-12 11:52
 **/
public interface ImageAttachmentService extends AttachmentService<ImageAttachment> {

    /**
     * 显示图片
     * @param response
     * @param attId
     */
    void getLocalImage(HttpServletResponse response, String attId);

    /**
     * 获取图片附件列表(分页)
     * @param start
     * @param pageSize
     * @param imageAttachmentQuery
     * @return
     */
    List<ImageAttachment> listImageAttachments(int start, int pageSize, ImageAttachmentQuery imageAttachmentQuery);

    /**
     * 获取图片附件列表
     * @param imageAttachmentQuery
     * @return
     */
    List<ImageAttachment> listImageAttachments(ImageAttachmentQuery imageAttachmentQuery);

    /**
     * 删除图片附件
     * @param id
     * @return
     */
    int deleteImageAttachmentById(String id);

    /**
     * 批量删除图片附件
     * @param ids
     * @return
     */
    int deleteBatchImageAttachmentByIds(List<String> ids);
}
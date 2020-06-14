package com.yk.fileupload.attachment.service;

import com.yk.fileupload.model.pojo.VideoAttachment;
import com.yk.fileupload.model.query.VideoAttachmentQuery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: YK-Platform
 * @description: 视频附件接口
 * @author: YuKai Fan
 * @create: 2020-06-12 16:23
 **/
public interface VideoAttachmentService extends AttachmentService<VideoAttachment> {

    /**
     * 获取视频附件列表(分页)
     * @param start
     * @param pageSize
     * @param videoAttachmentQuery
     * @return
     */
    List<VideoAttachment> listVideoAttachments(int start, int pageSize, VideoAttachmentQuery videoAttachmentQuery);

    /**
     * 获取视频附件列表
     * @param videoAttachmentQuery
     * @return
     */
    List<VideoAttachment> listVideoAttachments(VideoAttachmentQuery videoAttachmentQuery);

    /**
     * 删除视频附件
     * @param id
     * @return
     */
    int deleteVideoAttachmentById(String id);

    /**
     * 批量删除视频附件
     * @param ids
     * @return
     */
    int deleteBatchVideoAttachmentByIds(List<String> ids);

    /**
     * 获取视频播放流
     * @author YuKai Fan
     * @param request
     * @param response
     * @param attId
     * @return void
     * @date 2020/6/12 21:19
     */
    void getLocalVideo(HttpServletRequest request, HttpServletResponse response, String attId);
}
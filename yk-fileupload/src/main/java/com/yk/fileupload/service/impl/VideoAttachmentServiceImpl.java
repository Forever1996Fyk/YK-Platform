package com.yk.fileupload.service.impl;

import com.github.pagehelper.PageHelper;
import com.yk.common.util.AppUtils;
import com.yk.common.util.TimeUtils;
import com.yk.fileupload.mapper.VideoAttachmentMapper;
import com.yk.fileupload.model.pojo.VideoAttachment;
import com.yk.fileupload.model.query.VideoAttachmentQuery;
import com.yk.fileupload.service.VideoAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @program: YK-Platform
 * @description: 视频附件service实现类
 * @author: YuKai Fan
 * @create: 2020-06-10 20:15:50
 **/
@Service
@Transactional
public class VideoAttachmentServiceImpl implements VideoAttachmentService {
    @Autowired
    private VideoAttachmentMapper videoAttachmentMapper;

    @Override
    public int insertVideoAttachment(VideoAttachment videoAttachment) {
        videoAttachment.setId(AppUtils.randomId());
        videoAttachment.setStatus(1);
        videoAttachment.setCreateTime(TimeUtils.getCurrentDatetime());
        videoAttachment.setUpdateTime(TimeUtils.getCurrentDatetime());
        return videoAttachmentMapper.insertVideoAttachment(videoAttachment);
    }

    @Override
    public int insertVideoAttachmentBatch(List<VideoAttachment> list) {
        return videoAttachmentMapper.insertVideoAttachmentBatch(list);
    }

    @Override
    public int updateVideoAttachment(VideoAttachment videoAttachment) {
        videoAttachment.setUpdateTime(TimeUtils.getCurrentDatetime());
        return videoAttachmentMapper.updateVideoAttachment(videoAttachment);
    }

    @Override
    public int deleteVideoAttachmentById(String id) {
        return videoAttachmentMapper.deleteVideoAttachmentById(id);
    }

    @Override
    public int deleteBatchVideoAttachmentByIds(List<String> ids) {
        return videoAttachmentMapper.deleteBatchVideoAttachmentByIds(ids);
    }

    @Override
    public int deleteVideoAttachmentRealById(String id) {
        return videoAttachmentMapper.deleteVideoAttachmentRealById(id);
    }

    @Override
    public int deleteBatchVideoAttachmentRealByIds(List<String> list) {
        return videoAttachmentMapper.deleteBatchVideoAttachmentRealByIds(list);
    }

    @Override
    public VideoAttachment getVideoAttachmentById(String id) {
        return videoAttachmentMapper.getVideoAttachmentById(id);
    }

    @Override
    public List<VideoAttachment> listVideoAttachments(VideoAttachmentQuery videoAttachmentQuery) {
        return videoAttachmentMapper.listVideoAttachments(videoAttachmentQuery);
    }

    @Override
    public List<VideoAttachment> listVideoAttachments(int start, int pageSize, VideoAttachmentQuery videoAttachmentQuery) {
        PageHelper.startPage(start, pageSize);
        return this.listVideoAttachments(videoAttachmentQuery);
    }
    
}
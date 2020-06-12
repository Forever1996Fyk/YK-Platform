package com.yk.fileupload.attachment.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yk.common.enums.PositionTypeEnum;
import com.yk.common.exception.file.RequestToFileException;
import com.yk.common.util.FileUtils;
import com.yk.fileupload.attachment.service.VideoAttachmentService;
import com.yk.fileupload.mapper.VideoAttachmentMapper;
import com.yk.fileupload.model.pojo.ImageAttachment;
import com.yk.fileupload.model.pojo.VideoAttachment;
import com.yk.fileupload.model.query.VideoAttachmentQuery;
import com.yk.fileupload.util.local.LocalAttachmentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @program: YK-Platform
 * @description: 视频附件接口实现类
 * @author: YuKai Fan
 * @create: 2020-06-12 16:24
 **/
@Service
@Transactional
public class VideoAttachmentServiceImpl implements VideoAttachmentService {
    @Autowired
    private VideoAttachmentMapper videoAttachmentMapper;

    @Override
    public VideoAttachment uploadLocalAttachment(HttpServletRequest request, String ownerId) {
        MultipartFile file = FileUtils.getRequestFile(request);
        VideoAttachment attachment = null;
        try {
            attachment = LocalAttachmentUtils.getVideoAttachment(file, ownerId);
            FileUtils.transferTo(attachment.getAttachPath());

            attachment.setPositionType(PositionTypeEnum.LOCAL.getContent());
            videoAttachmentMapper.insertVideoAttachment(attachment);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attachment;
    }

    @Override
    public VideoAttachment uploadFastDFSAttachment(HttpServletRequest request, String ownerId) {
        return null;
    }

    @Override
    public VideoAttachment uploadOSSAttachment(HttpServletRequest request, String ownerId) {
        return null;
    }

    @Override
    public int uploadLocalBatchAttachment(HttpServletRequest request, String ownerId) {
        List<MultipartFile> requestListFile = FileUtils.getRequestListFile(request);
        if (CollectionUtils.isEmpty(requestListFile)) {
            throw new RequestToFileException();
        }
        int result = 1;
        List<VideoAttachment> list = Lists.newArrayList();
        for (MultipartFile file : requestListFile) {
            try {
                VideoAttachment attachment = LocalAttachmentUtils.getVideoAttachment(file, ownerId);
                FileUtils.transferTo(file.getInputStream(), attachment.getAttachPath());

                attachment.setPositionType(PositionTypeEnum.LOCAL.getContent());
                list.add(attachment);
            } catch (IOException e) {
                e.printStackTrace();
                result = 0;
            }
        }
        videoAttachmentMapper.insertVideoAttachmentBatch(list);
        return result;
    }

    @Override
    public int uploadFastDFSBatchAttachment(HttpServletRequest request, String ownerId) {
        return 0;
    }

    @Override
    public int uploadOSSBatchAttachment(HttpServletRequest request, String ownerId) {
        return 0;
    }

    @Override
    public void downloadAttachment() {

    }

    @Override
    public List<VideoAttachment> listVideoAttachments(int start, int pageSize, VideoAttachmentQuery videoAttachmentQuery) {
        PageHelper.startPage(start, pageSize);
        return this.listVideoAttachments(videoAttachmentQuery);
    }

    @Override
    public List<VideoAttachment> listVideoAttachments(VideoAttachmentQuery videoAttachmentQuery) {
        return videoAttachmentMapper.listVideoAttachments(videoAttachmentQuery);
    }

    @Override
    public int deleteVideoAttachmentById(String id) {
        return videoAttachmentMapper.deleteVideoAttachmentById(id);
    }

    @Override
    public int deleteBatchVideoAttachmentByIds(List<String> ids) {
        return videoAttachmentMapper.deleteBatchVideoAttachmentByIds(ids);
    }
}
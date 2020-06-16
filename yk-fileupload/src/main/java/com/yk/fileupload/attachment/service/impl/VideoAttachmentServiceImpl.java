package com.yk.fileupload.attachment.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yk.common.constant.ComConstants;
import com.yk.common.entity.Bucket;
import com.yk.common.enums.PositionTypeEnum;
import com.yk.common.exception.file.RequestToFileException;
import com.yk.common.util.FileUtils;
import com.yk.common.util.StringUtils;
import com.yk.fileupload.attachment.service.VideoAttachmentService;
import com.yk.fileupload.config.NonStaticResourceHttpRequestHandler;
import com.yk.fileupload.config.properties.FastDfsProperties;
import com.yk.fileupload.mapper.VideoAttachmentMapper;
import com.yk.fileupload.model.pojo.ImageAttachment;
import com.yk.fileupload.model.pojo.VideoAttachment;
import com.yk.fileupload.model.query.VideoAttachmentQuery;
import com.yk.fileupload.util.fastdfs.FastDfsAttachmentUtils;
import com.yk.fileupload.util.local.LocalAttachmentUtils;
import com.yk.fileupload.util.oss.AliyunOssUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @program: YK-Platform
 * @description: 视频附件接口实现类
 * @author: YuKai Fan
 * @create: 2020-06-12 16:24
 **/
@Service
@AllArgsConstructor
public class VideoAttachmentServiceImpl implements VideoAttachmentService {
    @Autowired
    private VideoAttachmentMapper videoAttachmentMapper;
    @Autowired
    private FastDfsProperties fastDfsProperties;

    private final NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;

    @Override
    public VideoAttachment uploadLocalAttachment(HttpServletRequest request, String ownerId, String attachAttr) {
        MultipartFile file = FileUtils.getRequestFile(request);
        VideoAttachment attachment = null;
        try {
            attachment = LocalAttachmentUtils.getVideoAttachment(file, ownerId, attachAttr);
            FileUtils.transferTo(file.getInputStream(), attachment.getAttachPath());

            attachment.setPositionType(PositionTypeEnum.LOCAL.getContent());
            videoAttachmentMapper.insertVideoAttachment(attachment);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attachment;
    }

    @Override
    public VideoAttachment uploadFastDFSAttachment(HttpServletRequest request, String ownerId, String attachAttr) {
        MultipartFile file = FileUtils.getRequestFile(request);
        VideoAttachment attachment = null;
        try {
            attachment = FastDfsAttachmentUtils.getVideoAttachment(file, ownerId, attachAttr);
            attachment.setPositionType(PositionTypeEnum.FASTDFS.getContent());
            videoAttachmentMapper.insertVideoAttachment(attachment);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attachment;
    }

    @Override
    public VideoAttachment uploadOssAttachment(HttpServletRequest request, String ownerId, String attachAttr, Bucket bucket) throws IOException {
        MultipartFile file = FileUtils.getRequestFile(request);
        VideoAttachment attachment = AliyunOssUtil.getVideoAttachment(file, ownerId, attachAttr, bucket);
        attachment.setPositionType(PositionTypeEnum.OSS.getContent());
        videoAttachmentMapper.insertVideoAttachment(attachment);
        return attachment;
    }

    @Override
    public int uploadLocalBatchAttachment(HttpServletRequest request, String ownerId, String attachAttr) {
        List<MultipartFile> requestListFile = FileUtils.getRequestListFile(request);
        if (CollectionUtils.isEmpty(requestListFile)) {
            throw new RequestToFileException();
        }
        int result = 1;
        List<VideoAttachment> list = Lists.newArrayList();
        for (MultipartFile file : requestListFile) {
            try {
                VideoAttachment attachment = LocalAttachmentUtils.getVideoAttachment(file, ownerId, attachAttr);
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
    public int uploadFastDFSBatchAttachment(HttpServletRequest request, String ownerId, String attachAttr) {
        List<MultipartFile> requestListFile = FileUtils.getRequestListFile(request);
        if (CollectionUtils.isEmpty(requestListFile)) {
            throw new RequestToFileException();
        }
        int result = 1;
        List<VideoAttachment> list = Lists.newArrayList();
        for (MultipartFile file : requestListFile) {
            try {
                VideoAttachment attachment = FastDfsAttachmentUtils.getVideoAttachment(file, ownerId, attachAttr);

                attachment.setPositionType(PositionTypeEnum.FASTDFS.getContent());
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
    public int uploadOssBatchAttachment(HttpServletRequest request, String ownerId, String attachAttr, Bucket bucket) {
        List<MultipartFile> requestListFile = FileUtils.getRequestListFile(request);
        if (CollectionUtils.isEmpty(requestListFile)) {
            throw new RequestToFileException();
        }
        int result = 1;
        List<VideoAttachment> list = Lists.newArrayList();
        for (MultipartFile file : requestListFile) {
            try {
                VideoAttachment attachment = AliyunOssUtil.getVideoAttachment(file, ownerId, attachAttr, bucket);
                attachment.setPositionType(PositionTypeEnum.OSS.getContent());
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
    public ResponseEntity<byte[]> downloadLocalAttachment(HttpServletRequest request, String attId) throws IOException {
        VideoAttachment attachment = videoAttachmentMapper.getVideoAttachmentById(attId);
        return LocalAttachmentUtils.downloadLocalAttachment(request, attachment);
    }

    @Override
    public ResponseEntity<byte[]> downloadFastDfsAttachment(HttpServletResponse response, String attId) {
        VideoAttachment attachment = videoAttachmentMapper.getVideoAttachmentById(attId);
        return FastDfsAttachmentUtils.downloadFastDfsAttachment(response, attachment);
    }

    @Override
    public void downloadOssAttachment(HttpServletResponse response, String attId) {
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

    @Override
    public void getLocalVideo(HttpServletRequest request, HttpServletResponse response, String attId) {
        VideoAttachment attachment = videoAttachmentMapper.getVideoAttachmentById(attId);
        File file = new File(attachment.getAttachPath());
        Path path = Paths.get(attachment.getAttachPath());
        try {
            if (Files.exists(path)) {
                String mimeType = null;
                mimeType = Files.probeContentType(path);
                if (StringUtils.isNotBlank(mimeType)) {
                    response.setContentType(mimeType);
                }
                request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, path);
                nonStaticResourceHttpRequestHandler.handleRequest(request, response);

            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.setCharacterEncoding(ComConstants.UTF8);
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public VideoAttachment getVideoAttachmentById(String attId) {
        return videoAttachmentMapper.getVideoAttachmentById(attId);
    }
}
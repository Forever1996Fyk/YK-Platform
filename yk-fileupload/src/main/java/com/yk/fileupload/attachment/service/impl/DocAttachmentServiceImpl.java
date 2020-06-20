package com.yk.fileupload.attachment.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yk.common.entity.Bucket;
import com.yk.common.enums.PositionTypeEnum;
import com.yk.common.exception.file.RequestToFileException;
import com.yk.common.util.FileUtils;
import com.yk.fileupload.attachment.service.DocAttachmentService;
import com.yk.fileupload.mapper.DocAttachmentMapper;
import com.yk.fileupload.model.pojo.DocAttachment;
import com.yk.fileupload.model.query.DocAttachmentQuery;
import com.yk.fileupload.util.fastdfs.FastDfsAttachmentUtils;
import com.yk.fileupload.util.local.LocalAttachmentUtils;
import com.yk.fileupload.util.oss.AliyunOssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program: YK-Platform
 * @description: 文档附件操作实现类
 * @author: YuKai Fan
 * @create: 2020-06-17 14:28
 **/
@Service
public class DocAttachmentServiceImpl implements DocAttachmentService {
    @Autowired
    private DocAttachmentMapper docAttachmentMapper;

    @Override
    public List<DocAttachment> listDocAttachments(int start, int pageSize, DocAttachmentQuery docAttachmentQuery) {
        PageHelper.startPage(start, pageSize);
        return this.listDocAttachments(docAttachmentQuery);
    }

    @Override
    public List<DocAttachment> listDocAttachments(DocAttachmentQuery docAttachmentQuery) {
        return docAttachmentMapper.listDocAttachments(docAttachmentQuery);
    }

    @Override
    public int deleteDocAttachmentById(String id) {
        return docAttachmentMapper.deleteDocAttachmentById(id);
    }

    @Override
    public int deleteBatchDocAttachmentByIds(List<String> ids) {
        return docAttachmentMapper.deleteBatchDocAttachmentByIds(ids);
    }

    @Override
    public void previewLocalDoc(HttpServletRequest request, HttpServletResponse response, String attId) throws IOException {
        DocAttachment attachment = docAttachmentMapper.getDocAttachmentById(attId);
        LocalAttachmentUtils.previewLocalDoc(response, attachment);
    }

    @Override
    public void previewFastDfsDoc(HttpServletRequest request, HttpServletResponse response, String attId) {

    }

    @Override
    public void previewOssDoc(HttpServletRequest request, HttpServletResponse response, String attId) {

    }

    @Override
    public DocAttachment uploadLocalAttachment(HttpServletRequest request, String ownerId, String attachAttr) throws IOException {
        MultipartFile file = FileUtils.getRequestFile(request);
        DocAttachment attachment = LocalAttachmentUtils.getDocAttachment(file, ownerId, attachAttr);
        FileUtils.transferTo(file.getInputStream(), attachment.getAttachPath());

        attachment.setPositionType(PositionTypeEnum.LOCAL.getContent());
        docAttachmentMapper.insertDocAttachment(attachment);
        return attachment;
    }

    @Override
    public DocAttachment uploadFastDFSAttachment(HttpServletRequest request, String ownerId, String attachAttr) throws IOException {
        MultipartFile file = FileUtils.getRequestFile(request);
        DocAttachment attachment = FastDfsAttachmentUtils.getDocAttachment(file, ownerId, attachAttr);
        attachment.setPositionType(PositionTypeEnum.FASTDFS.getContent());
        docAttachmentMapper.insertDocAttachment(attachment);
        return attachment;
    }

    @Override
    public DocAttachment uploadOssAttachment(HttpServletRequest request, String ownerId, String attachAttr, Bucket bucket) throws IOException {
        MultipartFile file = FileUtils.getRequestFile(request);
        DocAttachment attachment = AliyunOssUtil.getDocAttachment(file, ownerId, attachAttr, bucket);
        attachment.setPositionType(PositionTypeEnum.OSS.getContent());
        docAttachmentMapper.insertDocAttachment(attachment);
        return attachment;
    }

    @Override
    public int uploadLocalBatchAttachment(HttpServletRequest request, String ownerId, String attachAttr) throws IOException {
        List<MultipartFile> requestListFile = FileUtils.getRequestListFile(request);
        if (CollectionUtils.isEmpty(requestListFile)) {
            throw new RequestToFileException();
        }
        int result = 1;
        List<DocAttachment> list = Lists.newArrayList();
        for (MultipartFile file : requestListFile) {
            DocAttachment attachment = LocalAttachmentUtils.getDocAttachment(file, ownerId, attachAttr);
            FileUtils.transferTo(file.getInputStream(), attachment.getAttachPath());

            attachment.setPositionType(PositionTypeEnum.LOCAL.getContent());
            list.add(attachment);
        }
        docAttachmentMapper.insertDocAttachmentBatch(list);
        return result;
    }

    @Override
    public int uploadFastDFSBatchAttachment(HttpServletRequest request, String ownerId, String attachAttr) throws IOException {
        List<MultipartFile> requestListFile = FileUtils.getRequestListFile(request);
        if (CollectionUtils.isEmpty(requestListFile)) {
            throw new RequestToFileException();
        }
        int result = 1;
        List<DocAttachment> list = Lists.newArrayList();
        for (MultipartFile file : requestListFile) {
            DocAttachment attachment = FastDfsAttachmentUtils.getDocAttachment(file, ownerId, attachAttr);
            attachment.setPositionType(PositionTypeEnum.FASTDFS.getContent());
            list.add(attachment);
        }
        docAttachmentMapper.insertDocAttachmentBatch(list);
        return result;
    }

    @Override
    public int uploadOssBatchAttachment(HttpServletRequest request, String ownerId, String attachAttr, Bucket bucket) throws IOException {
        List<MultipartFile> requestListFile = FileUtils.getRequestListFile(request);
        if (CollectionUtils.isEmpty(requestListFile)) {
            throw new RequestToFileException();
        }
        int result = 1;
        List<DocAttachment> list = Lists.newArrayList();
        for (MultipartFile file : requestListFile) {
            DocAttachment attachment = AliyunOssUtil.getDocAttachment(file, ownerId, attachAttr, bucket);
            attachment.setPositionType(PositionTypeEnum.OSS.getContent());
            list.add(attachment);
        }
        docAttachmentMapper.insertDocAttachmentBatch(list);
        return result;
    }

    @Override
    public ResponseEntity<byte[]> downloadLocalAttachment(HttpServletRequest request, String attId) throws IOException {
        DocAttachment attachment = docAttachmentMapper.getDocAttachmentById(attId);
        return LocalAttachmentUtils.downloadLocalAttachment(request, attachment);
    }

    @Override
    public ResponseEntity<byte[]> downloadFastDfsAttachment(HttpServletResponse response, String attId) {
        DocAttachment attachment = docAttachmentMapper.getDocAttachmentById(attId);
        return FastDfsAttachmentUtils.downloadFastDfsAttachment(response, attachment);
    }

    @Override
    public ResponseEntity downloadOssAttachment(String attId) throws IOException {
        DocAttachment attachment = docAttachmentMapper.getDocAttachmentById(attId);
        return AliyunOssUtil.downloadOssObject(attachment);
    }
}
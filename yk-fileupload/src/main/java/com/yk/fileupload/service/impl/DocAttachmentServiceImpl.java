package com.yk.fileupload.service.impl;

import com.github.pagehelper.PageHelper;
import com.yk.common.util.AppUtils;
import com.yk.common.util.TimeUtils;
import com.yk.fileupload.mapper.DocAttachmentMapper;
import com.yk.fileupload.model.pojo.DocAttachment;
import com.yk.fileupload.model.query.DocAttachmentQuery;
import com.yk.fileupload.service.DocAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @program: YK-Platform
 * @description: 文档附件service实现类
 * @author: YuKai Fan
 * @create: 2020-06-10 20:15:50
 **/
@Service
@Transactional
public class DocAttachmentServiceImpl implements DocAttachmentService {
    @Autowired
    private DocAttachmentMapper docAttachmentMapper;

    @Override
    public int insertDocAttachment(DocAttachment docAttachment) {
        docAttachment.setId(AppUtils.randomId());
        docAttachment.setStatus(1);
        docAttachment.setCreateTime(TimeUtils.getCurrentDatetime());
        docAttachment.setUpdateTime(TimeUtils.getCurrentDatetime());
        return docAttachmentMapper.insertDocAttachment(docAttachment);
    }

    @Override
    public int insertDocAttachmentBatch(List<DocAttachment> list) {
        return docAttachmentMapper.insertDocAttachmentBatch(list);
    }

    @Override
    public int updateDocAttachment(DocAttachment docAttachment) {
        docAttachment.setUpdateTime(TimeUtils.getCurrentDatetime());
        return docAttachmentMapper.updateDocAttachment(docAttachment);
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
    public int deleteDocAttachmentRealById(String id) {
        return docAttachmentMapper.deleteDocAttachmentRealById(id);
    }

    @Override
    public int deleteBatchDocAttachmentRealByIds(List<String> list) {
        return docAttachmentMapper.deleteBatchDocAttachmentRealByIds(list);
    }

    @Override
    public DocAttachment getDocAttachmentById(String id) {
        return docAttachmentMapper.getDocAttachmentById(id);
    }

    @Override
    public List<DocAttachment> listDocAttachments(DocAttachmentQuery docAttachmentQuery) {
        return docAttachmentMapper.listDocAttachments(docAttachmentQuery);
    }

    @Override
    public List<DocAttachment> listDocAttachments(int start, int pageSize, DocAttachmentQuery docAttachmentQuery) {
        PageHelper.startPage(start, pageSize);
        return this.listDocAttachments(docAttachmentQuery);
    }
    
}
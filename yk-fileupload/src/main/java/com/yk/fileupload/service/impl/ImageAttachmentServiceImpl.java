package com.yk.fileupload.service.impl;

import com.github.pagehelper.PageHelper;
import com.yk.common.util.AppUtils;
import com.yk.common.util.TimeUtils;
import com.yk.fileupload.mapper.ImageAttachmentMapper;
import com.yk.fileupload.model.pojo.ImageAttachment;
import com.yk.fileupload.model.query.ImageAttachmentQuery;
import com.yk.fileupload.service.ImageAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @program: YK-Platform
 * @description: 图片附件service实现类
 * @author: YuKai Fan
 * @create: 2020-06-10 20:15:50
 **/
@Service
@Transactional
public class ImageAttachmentServiceImpl implements ImageAttachmentService {
    @Autowired
    private ImageAttachmentMapper imageAttachmentMapper;

    @Override
    public int insertImageAttachment(ImageAttachment imageAttachment) {
        imageAttachment.setId(AppUtils.randomId());
        imageAttachment.setStatus(1);
        imageAttachment.setCreateTime(TimeUtils.getCurrentDatetime());
        imageAttachment.setUpdateTime(TimeUtils.getCurrentDatetime());
        return imageAttachmentMapper.insertImageAttachment(imageAttachment);
    }

    @Override
    public int insertImageAttachmentBatch(List<ImageAttachment> list) {
        return imageAttachmentMapper.insertImageAttachmentBatch(list);
    }

    @Override
    public int updateImageAttachment(ImageAttachment imageAttachment) {
        imageAttachment.setUpdateTime(TimeUtils.getCurrentDatetime());
        return imageAttachmentMapper.updateImageAttachment(imageAttachment);
    }

    @Override
    public int deleteImageAttachmentById(String id) {
        return imageAttachmentMapper.deleteImageAttachmentById(id);
    }

    @Override
    public int deleteBatchImageAttachmentByIds(List<String> ids) {
        return imageAttachmentMapper.deleteBatchImageAttachmentByIds(ids);
    }

    @Override
    public int deleteImageAttachmentRealById(String id) {
        return imageAttachmentMapper.deleteImageAttachmentRealById(id);
    }

    @Override
    public int deleteBatchImageAttachmentRealByIds(List<String> list) {
        return imageAttachmentMapper.deleteBatchImageAttachmentRealByIds(list);
    }

    @Override
    public ImageAttachment getImageAttachmentById(String id) {
        return imageAttachmentMapper.getImageAttachmentById(id);
    }

    @Override
    public List<ImageAttachment> listImageAttachments(ImageAttachmentQuery imageAttachmentQuery) {
        return imageAttachmentMapper.listImageAttachments(imageAttachmentQuery);
    }

    @Override
    public List<ImageAttachment> listImageAttachments(int start, int pageSize, ImageAttachmentQuery imageAttachmentQuery) {
        PageHelper.startPage(start, pageSize);
        return this.listImageAttachments(imageAttachmentQuery);
    }
    
}
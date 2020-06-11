package com.yk.fileupload.attachment.service.impl;

import com.yk.common.enums.AttachmentTypeEnum;
import com.yk.common.util.FileUtils;
import com.yk.common.util.StringUtils;
import com.yk.common.util.TimeUtils;
import com.yk.fileupload.attachment.factory.AttachmentStrategyFactory;
import com.yk.fileupload.attachment.service.AttachmentService;
import com.yk.fileupload.manager.AsyncManager;
import com.yk.fileupload.manager.factory.AsyncFactory;
import com.yk.fileupload.mapper.ImageAttachmentMapper;
import com.yk.fileupload.model.pojo.ImageAttachment;
import com.yk.fileupload.util.local.LocalAttachmentUtils;
import com.yk.framework.util.ShiroUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: YK-Platform
 * @description: 图片附件操作实现类
 * @author: YuKai Fan
 * @create: 2020-06-10 22:32
 **/
@Service
@Transactional
public class ImageServiceImpl implements AttachmentService<ImageAttachment>, InitializingBean {
    @Autowired
    private ImageAttachmentMapper imageAttachmentMapper;

    @Override
    public ImageAttachment uploadAttachment(HttpServletRequest request) {
        MultipartFile file = FileUtils.getRequestFile(request);
        ImageAttachment attachment = LocalAttachmentUtils.getImageAttachment(file);
        FileUtils.transferTo(attachment.getAttachPath());

        attachment.setCreateTime(TimeUtils.getCurrentDatetime());
        attachment.setUpdateTime(TimeUtils.getCurrentDatetime());
        attachment.setCreateUserId(ShiroUtils.getCurrentUserId());
        attachment.setUpdateUserId(ShiroUtils.getCurrentUserId());
        imageAttachmentMapper.insertImageAttachment(attachment);
        return attachment;
    }

    @Override
    public ImageAttachment uploadAttachment(HttpServletRequest request, String ownerId) {
        return null;
    }

    @Override
    public ImageAttachment uploadAttachment(HttpServletRequest request, String ownerId, String uploadType) {
        return null;
    }

    @Override
    public Integer uploadBatchAttachment(HttpServletRequest request) {
        Map<String, MultipartFile> fileMap = FileUtils.getRequestFileMap(request);
        int result = 1;
        for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
            if (StringUtils.isBlank(entry.getKey())) {
                continue;
            }
            MultipartFile file = fileMap.get(entry.getKey());
            AsyncManager.asyncManager().execute(AsyncFactory.asyncLocalImageFileUpload(file));
        }
        return result;
    }

    @Override
    public Boolean uploadBatchAttachment(HttpServletRequest request, String ownerId) {
        return null;
    }

    @Override
    public Boolean uploadBatchAttachment(HttpServletRequest request, String ownerId, String uploadType) {
        return null;
    }

    @Override
    public void downloadAttachment() {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        AttachmentStrategyFactory.register(AttachmentTypeEnum.IMAGE.getValue(), this);
    }
}
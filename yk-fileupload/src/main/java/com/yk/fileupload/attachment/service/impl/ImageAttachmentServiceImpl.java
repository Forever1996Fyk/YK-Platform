package com.yk.fileupload.attachment.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yk.common.constant.ComConstants;
import com.yk.common.enums.PositionTypeEnum;
import com.yk.common.exception.file.FileReadErrorException;
import com.yk.common.exception.file.RequestToFileException;
import com.yk.common.util.FileUtils;
import com.yk.common.util.StringUtils;
import com.yk.fileupload.attachment.service.ImageAttachmentService;
import com.yk.fileupload.mapper.ImageAttachmentMapper;
import com.yk.fileupload.model.pojo.ImageAttachment;
import com.yk.fileupload.model.query.ImageAttachmentQuery;
import com.yk.fileupload.util.local.LocalAttachmentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @program: YK-Platform
 * @description: 图片附件操作实现类
 * @author: YuKai Fan
 * @create: 2020-06-10 22:32
 **/
@Service
@Transactional
public class ImageAttachmentServiceImpl implements ImageAttachmentService {
    @Autowired
    private ImageAttachmentMapper imageAttachmentMapper;

    @Override
    public ImageAttachment uploadLocalAttachment(HttpServletRequest request, String ownerId) {
        MultipartFile file = FileUtils.getRequestFile(request);
        ImageAttachment attachment = null;
        try {
            attachment = LocalAttachmentUtils.getImageAttachment(file, ownerId);
            FileUtils.transferTo(attachment.getAttachPath());

            attachment.setPositionType(PositionTypeEnum.LOCAL.getContent());
            imageAttachmentMapper.insertImageAttachment(attachment);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attachment;
    }

    @Override
    public ImageAttachment uploadFastDFSAttachment(HttpServletRequest request, String ownerId) {
        return null;
    }

    @Override
    public ImageAttachment uploadOSSAttachment(HttpServletRequest request, String ownerId) {
        return null;
    }

    @Override
    public int uploadLocalBatchAttachment(HttpServletRequest request, String ownerId) {
        List<MultipartFile> requestListFile = FileUtils.getRequestListFile(request);
        if (CollectionUtils.isEmpty(requestListFile)) {
            throw new RequestToFileException();
        }
        int result = 1;
        List<ImageAttachment> list = Lists.newArrayList();
        for (MultipartFile file : requestListFile) {
            try {
                ImageAttachment attachment = LocalAttachmentUtils.getImageAttachment(file, ownerId);
                FileUtils.transferTo(file.getInputStream(), attachment.getAttachPath());

                attachment.setPositionType(PositionTypeEnum.LOCAL.getContent());
                list.add(attachment);
            } catch (IOException e) {
                e.printStackTrace();
                result = 0;
            }
        }
        imageAttachmentMapper.insertImageAttachmentBatch(list);
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
    public void getLocalImage(HttpServletResponse response, String attId) {
        byte[] image = null;
        if (StringUtils.isBlank(attId)) {
            // 获取默认图片地址
        } else {
            ImageAttachment attachment = imageAttachmentMapper.getImageAttachmentById(attId);
            image = LocalAttachmentUtils.getLocalImage(attachment.getAttachPath());
        }

        response.setContentType("image/jpeg;charset=UTF-8");
        response.setCharacterEncoding(ComConstants.UTF8);
        try {
            OutputStream os = response.getOutputStream();
            os.write(image);
            os.flush();
            os.close();
        } catch (IOException e) {
            throw new FileReadErrorException();
        }
    }

    @Override
    public List<ImageAttachment> listImageAttachments(int start, int pageSize, ImageAttachmentQuery imageAttachmentQuery) {
        PageHelper.startPage(start, pageSize);
        return this.listImageAttachments(imageAttachmentQuery);
    }

    @Override
    public List<ImageAttachment> listImageAttachments(ImageAttachmentQuery imageAttachmentQuery) {
        return imageAttachmentMapper.listImageAttachments(imageAttachmentQuery);
    }

    @Override
    public int deleteImageAttachmentById(String id) {
        return imageAttachmentMapper.deleteImageAttachmentById(id);
    }

    @Override
    public int deleteBatchImageAttachmentByIds(List<String> ids) {
        return imageAttachmentMapper.deleteBatchImageAttachmentByIds(ids);
    }

}
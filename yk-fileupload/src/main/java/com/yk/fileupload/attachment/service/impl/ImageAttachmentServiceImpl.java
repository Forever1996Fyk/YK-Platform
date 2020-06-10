package com.yk.fileupload.attachment.service.impl;

import com.yk.common.entity.BaseAttachment;
import com.yk.common.util.FileUtils;
import com.yk.common.util.StringUtils;
import com.yk.fileupload.attachment.service.AttachmentService;
import com.yk.fileupload.model.pojo.ImageAttachment;
import com.yk.fileupload.service.ImageAttachmentService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: YK-Platform
 * @description: 图片附件操作实现类
 * @author: YuKai Fan
 * @create: 2020-06-10 22:32
 **/
@Component
public class ImageAttachmentServiceImpl extends AttachmentService implements InitializingBean {
    @Autowired
    private ImageAttachmentService imageAttachmentService;

    @Override
    protected ImageAttachment uploadAttachment(HttpServletRequest request) {
        MultipartFile file = FileUtils.getRequestFile(request);
        return null;
    }

    @Override
    protected ImageAttachment uploadAttachment(HttpServletRequest request, String ownerId) {
        return null;
    }

    @Override
    protected BaseAttachment uploadAttachment(HttpServletRequest request, String ownerId, String uploadType) {
        return null;
    }

    @Override
    protected Boolean uploadBatchAttachment(HttpServletRequest request) {
        Map<String, MultipartFile> fileMap = FileUtils.getRequestFileMap(request);
        for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
            if (StringUtils.isBlank(entry.getKey())) {
                continue;
            }
            MultipartFile file = fileMap.get(entry.getKey());
        }
        return null;
    }

    @Override
    protected Boolean uploadBatchAttachment(HttpServletRequest request, String ownerId) {
        return null;
    }

    @Override
    protected Boolean uploadBatchAttachment(HttpServletRequest request, String ownerId, String uploadType) {
        return null;
    }

    @Override
    protected void downloadAttachment() {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
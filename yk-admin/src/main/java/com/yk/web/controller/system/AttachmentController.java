package com.yk.web.controller.system;

import com.yk.common.dto.Result;
import com.yk.common.enums.AttachmentTypeEnum;
import com.yk.fileupload.attachment.factory.AttachmentStrategyFactory;
import com.yk.fileupload.attachment.service.AttachmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: YK-Platform
 * @description: 附件操作controller
 * @author: YuKai Fan
 * @create: 2020-06-11 15:30
 **/
@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

    @PostMapping("/uploadImageAttachment")
    public Result uploadImageAttachment(HttpServletRequest request) {
        AttachmentService attachmentService = AttachmentStrategyFactory.getAttachmentServiceByType(AttachmentTypeEnum.IMAGE.getValue());
        return Result.response(attachmentService.uploadBatchAttachment(request));
    }
}
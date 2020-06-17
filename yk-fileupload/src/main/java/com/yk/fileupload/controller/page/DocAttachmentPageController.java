package com.yk.fileupload.controller.page;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: YK-Platform
 * @description: 文档附件页面controller
 * @author: YuKai Fan
 * @create: 2020-06-17 15:10
 **/
@Controller
@RequestMapping("/attachment/doc")
public class DocAttachmentPageController {
    private final String prefix = "attachment/doc";

    /**
     * 跳转 文档文件列表
     * @return
     */
    @RequiresPermissions("attachment:doc:view")
    @GetMapping("/docAttachment")
    public String docAttachment() {
        return prefix + "/docAttachment";
    }

    /**
     * 跳转 批量上传文档页面
     * @return
     */
    @GetMapping("/uploadLocalDocs")
    public String uploadLocalDocs() {
        return prefix + "/uploadLocalDocs";
    }

    /**
     * 跳转 批量上传文档页面
     * @return
     */
    @GetMapping("/uploadFastDfsDocs")
    public String uploadFastDfsDocs() {
        return prefix + "/uploadFastDfsDocs";
    }
    /**
     * 跳转 批量上传文档页面
     * @return
     */
    @GetMapping("/uploadOssDocs")
    public String uploadOssDocs() {
        return prefix + "/uploadOssDocs";
    }

    /**
     * 跳转 上传单文档页面
     * @return
     */
    @GetMapping("/uploadDoc")
    public String uploadDoc() {
        return prefix + "/uploadDoc";
    }
}
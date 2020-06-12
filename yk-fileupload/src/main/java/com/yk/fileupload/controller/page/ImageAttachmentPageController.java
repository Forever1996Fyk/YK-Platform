package com.yk.fileupload.controller.page;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: YK-Platform
 * @description: 图片附件页面
 * @author: YuKai Fan
 * @create: 2020-06-11 15:53
 **/
@Controller
@RequestMapping("/attachment/image")
public class ImageAttachmentPageController {
    private final String prefix = "attachment/image";

    /**
     * 跳转 图片文件列表
     * @return
     */
    @RequiresPermissions("attachment:image:view")
    @GetMapping("/imageAttachment")
    public String imageAttachment() {
        return prefix + "/imageAttachment";
    }

    /**
     * 跳转 批量上传图片页面
     * @return
     */
    @GetMapping("/uploadImages")
    public String uploadImages() {
        return prefix + "/uploadImages";
    }

    /**
     * 跳转 上传单图片页面
     * @return
     */
    @GetMapping("/uploadImage")
    public String uploadImage() {
        return prefix + "/uploadImage";
    }
}
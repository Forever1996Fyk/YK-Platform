package com.yk.fileupload.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: YK-Platform
 * @description:
 * @author: YuKai Fan
 * @create: 2020-06-11 15:53
 **/
@Controller
@RequestMapping("/attachment/video")
public class VideoAttachmentPageController {
    private final String prefix = "attachment/video";

    /**
     * 跳转 视频文件列表
     * @return
     */
    @GetMapping("/videoAttachment")
    public String videoAttachment() {
        return prefix + "/videoAttachment";
    }

    /**
     * 跳转 批量上传视频页面
     * @return
     */
    @GetMapping("/uploadVideos")
    public String uploadVideos() {
        return prefix + "/uploadVideos";
    }

    /**
     * 跳转 上传单视频页面
     * @return
     */
    @GetMapping("/uploadVideo")
    public String uploadVideo() {
        return prefix + "/uploadVideo";
    }
}
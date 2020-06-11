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

    @GetMapping("/videoAttachment")
    public String videoAttachment() {
        return prefix + "/videoAttachment";
    }

    @GetMapping("/uploadVideos")
    public String uploadVideos() {
        return prefix + "/uploadVideos";
    }
}
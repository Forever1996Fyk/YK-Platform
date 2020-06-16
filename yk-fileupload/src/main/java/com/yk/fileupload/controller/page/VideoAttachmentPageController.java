package com.yk.fileupload.controller.page;

import com.yk.fileupload.attachment.service.VideoAttachmentService;
import com.yk.fileupload.mapper.VideoAttachmentMapper;
import com.yk.fileupload.model.pojo.VideoAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private VideoAttachmentService videoAttachmentService;

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
    @GetMapping("/uploadLocalVideos")
    public String uploadLocalVideos() {
        return prefix + "/uploadLocalVideos";
    }

    /**
     * 跳转 批量上传视频页面
     * @return
     */
    @GetMapping("/uploadFastDfsVideos")
    public String uploadFastDfsVideos() {
        return prefix + "/uploadFastDfsVideos";
    }

    /**
     * 跳转 批量上传视频页面
     * @return
     */
    @GetMapping("/uploadOssVideos")
    public String uploadOssVideos() {
        return prefix + "/uploadOssVideos";
    }

    /**
     * 跳转 上传单视频页面
     * @return
     */
    @GetMapping("/uploadVideo")
    public String uploadVideo() {
        return prefix + "/uploadVideo";
    }

    /**
     * 跳转 预览视频页面
     * @return
     */
    @GetMapping("/previewVideo/{attId}")
    public String previewVideo(@PathVariable("attId") String attId, Model model) {
        VideoAttachment attachment = videoAttachmentService.getVideoAttachmentById(attId);
        model.addAttribute("attachment", attachment);
        return prefix + "/previewVideo";
    }
}
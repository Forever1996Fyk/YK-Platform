package com.yk.fileupload.controller;

import com.yk.common.dto.DataTablesViewPage;
import com.yk.common.dto.Result;
import com.yk.fileupload.attachment.service.VideoAttachmentService;
import com.yk.fileupload.model.pojo.VideoAttachment;
import com.yk.fileupload.model.query.VideoAttachmentQuery;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: YK-Platform
 * @description: 视频附件controller
 * @author: YuKai Fan
 * @create: 2020-06-12 16:30
 **/
@RestController
@RequestMapping("/api/videoAttachment")
public class VideoAttachmentController {
    @Autowired
    private VideoAttachmentService videoAttachmentService;

    /**
     * 获取视频附件列表
     * @param start
     * @param pageSize
     * @param videoAttachmentQuery
     * @return
     */
    @GetMapping("/listVideoAttachments")
    @RequiresPermissions("attachment:video:list")
    public Result listVideoAttachments(@RequestParam(value = "start", defaultValue = "0") int start,
                                       @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                       VideoAttachmentQuery videoAttachmentQuery) {
        List<VideoAttachment> list = videoAttachmentService.listVideoAttachments(start, pageSize, videoAttachmentQuery);
        return Result.success("获取成功", new DataTablesViewPage<>(list));
    }

    /**
     * 本地上传 视频上传附件
     * @param request
     * @return
     */
    @PostMapping("/uploadLocalAttachment")
    @RequiresPermissions("attachment:video:upload")
    public Result uploadLocalAttachment(HttpServletRequest request) {
        VideoAttachment attachment = videoAttachmentService.uploadLocalAttachment(request, null);
        return Result.success("上传成功", attachment);
    }

    /**
     * 本地上传 视频上传附件
     * @param request
     * @param ownerId
     * @return
     */
    @PostMapping("/uploadLocalAttachment/{ownerId}")
    @RequiresPermissions("attachment:video:upload")
    public Result uploadLocalAttachment(HttpServletRequest request, @PathVariable("ownerId") String ownerId) {
        VideoAttachment attachment = videoAttachmentService.uploadLocalAttachment(request, ownerId);
        return Result.success("上传成功", attachment);
    }

    /**
     * 批量本地上传 视频上传附件
     * @param request
     * @return
     */
    @PostMapping("/uploadLocalBatchAttachment")
    @RequiresPermissions("attachment:video:upload")
    public Result uploadLocalBatchAttachment(HttpServletRequest request) {
        return Result.response(videoAttachmentService.uploadLocalBatchAttachment(request, null));
    }

    /**
     * 批量本地上传 视频上传附件
     * @param request
     * @param ownerId
     * @return
     */
    @PostMapping("/uploadLocalBatchAttachment/{ownerId}")
    @RequiresPermissions("attachment:video:upload")
    public Result uploadLocalBatchAttachment(HttpServletRequest request, @PathVariable("ownerId") String ownerId) {
        return Result.response(videoAttachmentService.uploadLocalBatchAttachment(request, ownerId));
    }

    /**
     * 删除本地视频附件
     * @param id
     * @return
     */
    @DeleteMapping("/deleteVideoAttachmentById/{id}")
    @RequiresPermissions("attachment:video:delete")
    public Result deleteVideoAttachmentById(@PathVariable("id") String id) {
        return Result.response(videoAttachmentService.deleteVideoAttachmentById(id));
    }

    /**
     * 批量删除本地视频附件
     * @param id
     * @return
     */
    @DeleteMapping("/deleteBatchVideoAttachmentByIds/{ids}")
    @RequiresPermissions("attachment:video:delete")
    public Result deleteBatchVideoAttachmentByIds(@PathVariable("ids") List<String> ids) {
        return Result.response(videoAttachmentService.deleteBatchVideoAttachmentByIds(ids));
    }
}
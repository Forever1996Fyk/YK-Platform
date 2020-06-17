package com.yk.fileupload.controller;

import com.yk.common.dto.DataTablesViewPage;
import com.yk.common.dto.Result;
import com.yk.common.entity.Bucket;
import com.yk.common.util.MapUtils;
import com.yk.common.util.ServletUtils;
import com.yk.fileupload.attachment.service.VideoAttachmentService;
import com.yk.fileupload.model.pojo.VideoAttachment;
import com.yk.fileupload.model.query.VideoAttachmentQuery;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    public Result uploadLocalAttachment(HttpServletRequest request) throws IOException {
        VideoAttachment attachment = videoAttachmentService.uploadLocalAttachment(request, null, null);
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
    public Result uploadLocalAttachment(HttpServletRequest request, @PathVariable("ownerId") String ownerId) throws IOException {
        VideoAttachment attachment = videoAttachmentService.uploadLocalAttachment(request, ownerId, null);
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
        return Result.response(videoAttachmentService.uploadLocalBatchAttachment(request, null, null));
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
        return Result.response(videoAttachmentService.uploadLocalBatchAttachment(request, ownerId, null));
    }

    /**
     * FastDfs上传 视频上传附件
     * @param request
     * @return
     */
    @PostMapping("/uploadFastDfsAttachment")
    @RequiresPermissions("attachment:video:upload")
    public Result uploadFastDfsAttachment(HttpServletRequest request) throws IOException {
        VideoAttachment attachment = videoAttachmentService.uploadFastDFSAttachment(request, null, null);
        return Result.success("上传成功", attachment);
    }

    /**
     * FastDfs上传 视频上传附件
     * @param request
     * @param ownerId
     * @return
     */
    @PostMapping("/uploadFastDfsAttachment/{ownerId}")
    @RequiresPermissions("attachment:video:upload")
    public Result uploadFastDfsAttachment(HttpServletRequest request, @PathVariable("ownerId") String ownerId) throws IOException {
        VideoAttachment attachment = videoAttachmentService.uploadFastDFSAttachment(request, ownerId, null);
        return Result.success("上传成功", attachment);
    }

    /**
     * 批量FastDfs上传 视频上传附件
     * @param request
     * @return
     */
    @PostMapping("/uploadFastDfsBatchAttachment")
    @RequiresPermissions("attachment:video:upload")
    public Result uploadFastDfsBatchAttachment(HttpServletRequest request) {
        return Result.response(videoAttachmentService.uploadFastDFSBatchAttachment(request, null, null));
    }

    /**
     * 批量FastDfs上传 视频上传附件
     * @param request
     * @param ownerId
     * @return
     */
    @PostMapping("/uploadFastDfsBatchAttachment/{ownerId}")
    @RequiresPermissions("attachment:video:upload")
    public Result uploadFastDfsBatchAttachment(HttpServletRequest request, @PathVariable("ownerId") String ownerId) {
        return Result.response(videoAttachmentService.uploadFastDFSBatchAttachment(request, ownerId, null));
    }

    /**
     * Oss上传 视频上传附件
     * @param request
     * @return
     */
    @PostMapping("/uploadOssAttachment")
    @RequiresPermissions("attachment:video:upload")
    public Result uploadOssAttachment(HttpServletRequest request) throws IOException {
        Map<String, Object> parameterMap = ServletUtils.getParameterMapObject(request);
        Bucket bucket = MapUtils.mapToObject(Bucket.class, parameterMap, false);
        VideoAttachment attachment = videoAttachmentService.uploadOssAttachment(request, null, null, bucket);
        return Result.success("上传成功", attachment);
    }

    /**
     * Oss上传 视频上传附件
     * @param request
     * @param ownerId
     * @return
     */
    @PostMapping("/uploadOssAttachment/{ownerId}")
    @RequiresPermissions("attachment:video:upload")
    public Result uploadOssAttachment(HttpServletRequest request, @PathVariable("ownerId") String ownerId) throws IOException {
        Map<String, Object> parameterMap = ServletUtils.getParameterMapObject(request);
        Bucket bucket = MapUtils.mapToObject(Bucket.class, parameterMap, false);
        VideoAttachment attachment = videoAttachmentService.uploadOssAttachment(request, ownerId, null, bucket);
        return Result.success("上传成功", attachment);
    }

    /**
     * 批量Oss上传 视频上传附件
     * @param request
     * @return
     */
    @PostMapping("/uploadOssBatchAttachment")
    @RequiresPermissions("attachment:video:upload")
    public Result uploadOssBatchAttachment(HttpServletRequest request) {
        Map<String, Object> parameterMap = ServletUtils.getParameterMapObject(request);
        Bucket bucket = MapUtils.mapToObject(Bucket.class, parameterMap, false);
        return Result.response(videoAttachmentService.uploadOssBatchAttachment(request, null, null, bucket));
    }

    /**
     * 批量Oss上传 视频上传附件
     * @param request
     * @param ownerId
     * @return
     */
    @PostMapping("/uploadOssBatchAttachment/{ownerId}")
    @RequiresPermissions("attachment:video:upload")
    public Result uploadOssBatchAttachment(HttpServletRequest request, @PathVariable("ownerId") String ownerId) {
        Map<String, Object> parameterMap = ServletUtils.getParameterMapObject(request);
        Bucket bucket = MapUtils.mapToObject(Bucket.class, parameterMap, false);
        return Result.response(videoAttachmentService.uploadOssBatchAttachment(request, ownerId, null, bucket));
    }

    /**
     * 获取视频播放流
     * @author YuKai Fan
     * @param request
     * @param response
     * @param attId
     * @return void
     * @date 2020/6/12 21:18
     */
    @GetMapping("/getVideoStream/{attId}")
    public void getLocalVideo(HttpServletRequest request, HttpServletResponse response, @PathVariable("attId") String attId) {
        videoAttachmentService.getLocalVideo(request, response, attId);
    }

    /**
     * 下载本地视频附件
     * @author YuKai Fan
     * @param attId
     * @return void
     * @date 2020/6/14 18:57
     */
    @GetMapping("/downloadLocalVideoAttachment/{attId}")
    public ResponseEntity<byte[]> downloadLocalVideoAttachment(HttpServletRequest request, @PathVariable("attId") String attId) throws IOException {
        return videoAttachmentService.downloadLocalAttachment(request, attId);
    }

    /**
     * 下载FastDfs视频附件
     * @author YuKai Fan
     * @param attId
     * @return void
     * @date 2020/6/14 18:57
     */
    @GetMapping("/downloadFastDfsAttachment/{attId}")
    public ResponseEntity<byte[]> downloadFastDfsAttachment(HttpServletResponse response, @PathVariable("attId") String attId) throws IOException {
         return videoAttachmentService.downloadFastDfsAttachment(response, attId);
    }

    /**
     * 下载OSS视频附件
     * @author YuKai Fan
     * @param attId
     * @return void
     * @date 2020/6/14 18:57
     */
    @GetMapping("/downloadOssAttachment/{attId}")
    public ResponseEntity downloadOssAttachment(@PathVariable("attId") String attId) throws IOException {
        return videoAttachmentService.downloadOssAttachment(attId);
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
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteBatchVideoAttachmentByIds/{ids}")
    @RequiresPermissions("attachment:video:delete")
    public Result deleteBatchVideoAttachmentByIds(@PathVariable("ids") List<String> ids) {
        return Result.response(videoAttachmentService.deleteBatchVideoAttachmentByIds(ids));
    }
}
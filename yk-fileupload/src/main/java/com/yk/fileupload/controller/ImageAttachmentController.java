package com.yk.fileupload.controller;

import com.yk.common.dto.DataTablesViewPage;
import com.yk.common.dto.Result;
import com.yk.common.entity.Bucket;
import com.yk.common.util.MapUtils;
import com.yk.common.util.ServletUtils;
import com.yk.fileupload.attachment.service.ImageAttachmentService;
import com.yk.fileupload.model.pojo.ImageAttachment;
import com.yk.fileupload.model.query.ImageAttachmentQuery;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @program: YK-Platform
 * @description: 图片附件上传
 * @author: YuKai Fan
 * @create: 2020-06-12 13:51
 **/
@RestController
@RequestMapping("/api/imageAttachment")
public class ImageAttachmentController {
    @Autowired
    private ImageAttachmentService imageAttachmentService;

    /**
     * 获取图片附件列表
     * @param start
     * @param pageSize
     * @param imageAttachmentQuery
     * @return
     */
    @GetMapping("/listImageAttachments")
    @RequiresPermissions("attachment:image:list")
    public Result listImageAttachments(@RequestParam(value = "start", defaultValue = "0") int start,
                                       @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                       ImageAttachmentQuery imageAttachmentQuery) {
        List<ImageAttachment> list = imageAttachmentService.listImageAttachments(start, pageSize, imageAttachmentQuery);
        return Result.success("获取成功", new DataTablesViewPage<>(list));
    }

    /**
     * 本地上传 图片上传附件
     * @param request
     * @return
     */
    @PostMapping("/uploadLocalAttachment")
    @RequiresPermissions("attachment:image:upload")
    public Result uploadLocalAttachment(HttpServletRequest request) throws IOException {
        ImageAttachment attachment = imageAttachmentService.uploadLocalAttachment(request, null, null);
        return Result.success("上传成功", attachment);
    }

    /**
     * 本地上传 图片上传附件
     * @param request
     * @param ownerId
     * @return
     */
    @PostMapping("/uploadLocalAttachment/{ownerId}")
    @RequiresPermissions("attachment:image:upload")
    public Result uploadLocalAttachment(HttpServletRequest request, @PathVariable("ownerId") String ownerId) throws IOException {
        ImageAttachment attachment = imageAttachmentService.uploadLocalAttachment(request, ownerId, null);
        return Result.success("上传成功", attachment);
    }

    /**
     * 批量本地上传 图片上传附件
     * @param request
     * @return
     */
    @PostMapping("/uploadLocalBatchAttachment")
    @RequiresPermissions("attachment:image:upload")
    public Result uploadLocalBatchAttachment(HttpServletRequest request) throws IOException {
        return Result.response(imageAttachmentService.uploadLocalBatchAttachment(request, null, null));
    }

    /**
     * 批量本地上传 图片上传附件
     * @param request
     * @param ownerId
     * @return
     */
    @PostMapping("/uploadLocalBatchAttachment/{ownerId}")
    @RequiresPermissions("attachment:image:upload")
    public Result uploadLocalBatchAttachment(HttpServletRequest request, @PathVariable("ownerId") String ownerId) throws IOException {
        return Result.response(imageAttachmentService.uploadLocalBatchAttachment(request, ownerId, null));
    }

    /**
     * fastDFS上传 图片上传附件
     * @param request
     * @return
     */
    @PostMapping("/uploadFastDfsAttachment")
    @RequiresPermissions("attachment:image:upload")
    public Result uploadFastDFSAttachment(HttpServletRequest request) throws IOException {
        ImageAttachment attachment = imageAttachmentService.uploadFastDFSAttachment(request, null, null);
        return Result.success("上传成功", attachment);
    }

    /**
     * fastDFS上传 图片上传附件
     * @param request
     * @param ownerId
     * @return
     */
    @PostMapping("/uploadFastDfsAttachment/{ownerId}")
    @RequiresPermissions("attachment:image:upload")
    public Result uploadFastDFSAttachment(HttpServletRequest request, @PathVariable("ownerId") String ownerId) throws IOException {
        ImageAttachment attachment = imageAttachmentService.uploadFastDFSAttachment(request, ownerId, null);
        return Result.success("上传成功", attachment);
    }

    /**
     * 批量fastDFS上传 图片上传附件
     * @param request
     * @return
     */
    @PostMapping("/uploadFastDfsBatchAttachment")
    @RequiresPermissions("attachment:image:upload")
    public Result uploadFastDFSBatchAttachment(HttpServletRequest request) throws IOException {
        return Result.response(imageAttachmentService.uploadFastDFSBatchAttachment(request, null, null));
    }

    /**
     * 批量fastDFS上传 图片上传附件
     * @param request
     * @param ownerId
     * @return
     */
    @PostMapping("/uploadFastDfsBatchAttachment/{ownerId}")
    @RequiresPermissions("attachment:image:upload")
    public Result uploadFastDFSBatchAttachment(HttpServletRequest request, @PathVariable("ownerId") String ownerId) throws IOException {
        return Result.response(imageAttachmentService.uploadFastDFSBatchAttachment(request, ownerId, null));
    }

    /**
     * OSS上传 图片上传附件
     * @param request
     * @return
     */
    @PostMapping("/uploadOssAttachment")
    @RequiresPermissions("attachment:image:upload")
    public Result uploadOssAttachment(HttpServletRequest request) throws IOException {
        Map<String, Object> parameterMap = ServletUtils.getParameterMapObject(request);
        Bucket bucket = MapUtils.mapToObject(Bucket.class, parameterMap, false);
        ImageAttachment attachment = imageAttachmentService.uploadOssAttachment(request, null, null, bucket);
        return Result.success("上传成功", attachment);
    }

    /**
     * OSS上传 图片上传附件
     * @param request
     * @param ownerId
     * @return
     */
    @PostMapping("/uploadOssAttachment/{ownerId}")
    @RequiresPermissions("attachment:image:upload")
    public Result uploadOssAttachment(HttpServletRequest request, @PathVariable("ownerId") String ownerId) throws IOException {
        Map<String, Object> parameterMap = ServletUtils.getParameterMapObject(request);
        Bucket bucket = MapUtils.mapToObject(Bucket.class, parameterMap, false);
        ImageAttachment attachment = imageAttachmentService.uploadOssAttachment(request, ownerId, null, bucket);
        return Result.success("上传成功", attachment);
    }

    /**
     * 批量OSS上传 图片上传附件
     * @param request
     * @return
     */
    @PostMapping("/uploadOssBatchAttachment")
    @RequiresPermissions("attachment:image:upload")
    public Result uploadOssBatchAttachment(HttpServletRequest request) throws IOException {
        Map<String, Object> parameterMap = ServletUtils.getParameterMapObject(request);
        Bucket bucket = MapUtils.mapToObject(Bucket.class, parameterMap, false);
        return Result.response(imageAttachmentService.uploadOssBatchAttachment(request, null, null, bucket));
    }

    /**
     * 批量OSS上传 图片上传附件
     * @param request
     * @param ownerId
     * @return
     */
    @PostMapping("/uploadOssBatchAttachment/{ownerId}")
    @RequiresPermissions("attachment:image:upload")
    public Result uploadOssBatchAttachment(HttpServletRequest request, @PathVariable("ownerId") String ownerId) throws IOException {
        Map<String, Object> parameterMap = ServletUtils.getParameterMapObject(request);
        Bucket bucket = MapUtils.mapToObject(Bucket.class, parameterMap, false);
        return Result.response(imageAttachmentService.uploadOssBatchAttachment(request, ownerId, null, bucket));
    }

    /**
     * 获取图片
     * @param response
     * @param attId
     */
    @GetMapping("/showImage/{attId}")
    @RequiresPermissions("attachment:image:get")
    public void getLocalImage(HttpServletResponse response, @PathVariable("attId") String attId) {
        imageAttachmentService.getLocalImage(response, attId);
    }

    /**
     * 删除本地图片附件
     * @param id
     * @return
     */
    @DeleteMapping("/deleteImageAttachmentById/{id}")
    @RequiresPermissions("attachment:image:delete")
    public Result deleteImageAttachmentById(@PathVariable("id") String id) {
        return Result.response(imageAttachmentService.deleteImageAttachmentById(id));
    }

    /**
     * 批量删除本地图片附件
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteBatchImageAttachmentByIds/{ids}")
    @RequiresPermissions("attachment:image:delete")
    public Result deleteBatchImageAttachmentByIds(@PathVariable("ids") List<String> ids) {
        return Result.response(imageAttachmentService.deleteBatchImageAttachmentByIds(ids));
    }
}
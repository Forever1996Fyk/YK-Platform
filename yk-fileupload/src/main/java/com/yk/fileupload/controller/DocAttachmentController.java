package com.yk.fileupload.controller;

import com.yk.common.dto.DataTablesViewPage;
import com.yk.common.dto.Result;
import com.yk.common.entity.Bucket;
import com.yk.common.util.MapUtils;
import com.yk.common.util.ServletUtils;
import com.yk.fileupload.attachment.service.DocAttachmentService;
import com.yk.fileupload.model.pojo.DocAttachment;
import com.yk.fileupload.model.query.DocAttachmentQuery;
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
 * @description: 文档附件controller
 * @author: YuKai Fan
 * @create: 2020-06-17 15:09
 **/
@RestController
@RequestMapping("/api/docAttachment")
public class DocAttachmentController {
    @Autowired
    private DocAttachmentService docAttachmentService;

    /**
     * 获取文档附件列表
     * @param start
     * @param pageSize
     * @param docAttachmentQuery
     * @return
     */
    @GetMapping("/listDocAttachments")
    @RequiresPermissions("attachment:doc:list")
    public Result listDocAttachments(@RequestParam(value = "start", defaultValue = "0") int start,
                                       @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                       DocAttachmentQuery docAttachmentQuery) {
        List<DocAttachment> list = docAttachmentService.listDocAttachments(start, pageSize, docAttachmentQuery);
        return Result.success("获取成功", new DataTablesViewPage<>(list));
    }

    /**
     * 本地上传 文档上传附件
     * @param request
     * @return
     */
    @PostMapping("/uploadLocalAttachment")
    @RequiresPermissions("attachment:doc:upload")
    public Result uploadLocalAttachment(HttpServletRequest request) throws IOException {
        DocAttachment attachment = docAttachmentService.uploadLocalAttachment(request, null, null);
        return Result.success("上传成功", attachment);
    }

    /**
     * 本地上传 文档上传附件
     * @param request
     * @param ownerId
     * @return
     */
    @PostMapping("/uploadLocalAttachment/{ownerId}")
    @RequiresPermissions("attachment:doc:upload")
    public Result uploadLocalAttachment(HttpServletRequest request, @PathVariable("ownerId") String ownerId) throws IOException {
        DocAttachment attachment = docAttachmentService.uploadLocalAttachment(request, ownerId, null);
        return Result.success("上传成功", attachment);
    }

    /**
     * 批量本地上传 文档上传附件
     * @param request
     * @return
     */
    @PostMapping("/uploadLocalBatchAttachment")
    @RequiresPermissions("attachment:doc:upload")
    public Result uploadLocalBatchAttachment(HttpServletRequest request) {
        return Result.response(docAttachmentService.uploadLocalBatchAttachment(request, null, null));
    }

    /**
     * 批量本地上传 文档上传附件
     * @param request
     * @param ownerId
     * @return
     */
    @PostMapping("/uploadLocalBatchAttachment/{ownerId}")
    @RequiresPermissions("attachment:doc:upload")
    public Result uploadLocalBatchAttachment(HttpServletRequest request, @PathVariable("ownerId") String ownerId) {
        return Result.response(docAttachmentService.uploadLocalBatchAttachment(request, ownerId, null));
    }

    /**
     * FastDfs上传 文档上传附件
     * @param request
     * @return
     */
    @PostMapping("/uploadFastDfsAttachment")
    @RequiresPermissions("attachment:doc:upload")
    public Result uploadFastDfsAttachment(HttpServletRequest request) throws IOException {
        DocAttachment attachment = docAttachmentService.uploadFastDFSAttachment(request, null, null);
        return Result.success("上传成功", attachment);
    }

    /**
     * FastDfs上传 文档上传附件
     * @param request
     * @param ownerId
     * @return
     */
    @PostMapping("/uploadFastDfsAttachment/{ownerId}")
    @RequiresPermissions("attachment:doc:upload")
    public Result uploadFastDfsAttachment(HttpServletRequest request, @PathVariable("ownerId") String ownerId) throws IOException {
        DocAttachment attachment = docAttachmentService.uploadFastDFSAttachment(request, ownerId, null);
        return Result.success("上传成功", attachment);
    }

    /**
     * 批量FastDfs上传 文档上传附件
     * @param request
     * @return
     */
    @PostMapping("/uploadFastDfsBatchAttachment")
    @RequiresPermissions("attachment:doc:upload")
    public Result uploadFastDfsBatchAttachment(HttpServletRequest request) {
        return Result.response(docAttachmentService.uploadFastDFSBatchAttachment(request, null, null));
    }

    /**
     * 批量FastDfs上传 文档上传附件
     * @param request
     * @param ownerId
     * @return
     */
    @PostMapping("/uploadFastDfsBatchAttachment/{ownerId}")
    @RequiresPermissions("attachment:doc:upload")
    public Result uploadFastDfsBatchAttachment(HttpServletRequest request, @PathVariable("ownerId") String ownerId) {
        return Result.response(docAttachmentService.uploadFastDFSBatchAttachment(request, ownerId, null));
    }

    /**
     * Oss上传 文档上传附件
     * @param request
     * @return
     */
    @PostMapping("/uploadOssAttachment")
    @RequiresPermissions("attachment:doc:upload")
    public Result uploadOssAttachment(HttpServletRequest request) throws IOException {
        Map<String, Object> parameterMap = ServletUtils.getParameterMapObject(request);
        Bucket bucket = MapUtils.mapToObject(Bucket.class, parameterMap, false);
        DocAttachment attachment = docAttachmentService.uploadOssAttachment(request, null, null, bucket);
        return Result.success("上传成功", attachment);
    }

    /**
     * Oss上传 文档上传附件
     * @param request
     * @param ownerId
     * @return
     */
    @PostMapping("/uploadOssAttachment/{ownerId}")
    @RequiresPermissions("attachment:doc:upload")
    public Result uploadOssAttachment(HttpServletRequest request, @PathVariable("ownerId") String ownerId) throws IOException {
        Map<String, Object> parameterMap = ServletUtils.getParameterMapObject(request);
        Bucket bucket = MapUtils.mapToObject(Bucket.class, parameterMap, false);
        DocAttachment attachment = docAttachmentService.uploadOssAttachment(request, ownerId, null, bucket);
        return Result.success("上传成功", attachment);
    }

    /**
     * 批量Oss上传 文档上传附件
     * @param request
     * @return
     */
    @PostMapping("/uploadOssBatchAttachment")
    @RequiresPermissions("attachment:doc:upload")
    public Result uploadOssBatchAttachment(HttpServletRequest request) {
        Map<String, Object> parameterMap = ServletUtils.getParameterMapObject(request);
        Bucket bucket = MapUtils.mapToObject(Bucket.class, parameterMap, false);
        return Result.response(docAttachmentService.uploadOssBatchAttachment(request, null, null, bucket));
    }

    /**
     * 批量Oss上传 文档上传附件
     * @param request
     * @param ownerId
     * @return
     */
    @PostMapping("/uploadOssBatchAttachment/{ownerId}")
    @RequiresPermissions("attachment:doc:upload")
    public Result uploadOssBatchAttachment(HttpServletRequest request, @PathVariable("ownerId") String ownerId) {
        Map<String, Object> parameterMap = ServletUtils.getParameterMapObject(request);
        Bucket bucket = MapUtils.mapToObject(Bucket.class, parameterMap, false);
        return Result.response(docAttachmentService.uploadOssBatchAttachment(request, ownerId, null, bucket));
    }

    /**
     * 下载本地文档附件
     * @author YuKai Fan
     * @param attId
     * @return void
     * @date 2020/6/14 18:57
     */
    @GetMapping("/downloadLocalDocAttachment/{attId}")
    @RequiresPermissions("attachment:doc:download")
    public ResponseEntity<byte[]> downloadLocalDocAttachment(HttpServletRequest request, @PathVariable("attId") String attId) throws IOException {
        return docAttachmentService.downloadLocalAttachment(request, attId);
    }

    /**
     * 下载FastDfs文档附件
     * @author YuKai Fan
     * @param attId
     * @return void
     * @date 2020/6/14 18:57
     */
    @GetMapping("/downloadFastDfsAttachment/{attId}")
    @RequiresPermissions("attachment:doc:download")
    public ResponseEntity<byte[]> downloadFastDfsAttachment(HttpServletResponse response, @PathVariable("attId") String attId) throws IOException {
        return docAttachmentService.downloadFastDfsAttachment(response, attId);
    }

    /**
     * 下载OSS文档附件
     * @author YuKai Fan
     * @param attId
     * @return void
     * @date 2020/6/14 18:57
     */
    @GetMapping("/downloadOssAttachment/{attId}")
    @RequiresPermissions("attachment:doc:download")
    public ResponseEntity downloadOssAttachment(@PathVariable("attId") String attId) throws IOException {
        return docAttachmentService.downloadOssAttachment(attId);
    }

    /**
     * 删除本地文档附件
     * @param id
     * @return
     */
    @DeleteMapping("/deleteDocAttachmentById/{id}")
    @RequiresPermissions("attachment:doc:delete")
    public Result deleteDocAttachmentById(@PathVariable("id") String id) {
        return Result.response(docAttachmentService.deleteDocAttachmentById(id));
    }

    /**
     * 批量删除本地文档附件
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteBatchDocAttachmentByIds/{ids}")
    @RequiresPermissions("attachment:doc:delete")
    public Result deleteBatchDocAttachmentByIds(@PathVariable("ids") List<String> ids) {
        return Result.response(docAttachmentService.deleteBatchDocAttachmentByIds(ids));
    }

    /**
     * 预览文档
     * @param request
     * @param response
     * @param attId
     */
    @GetMapping("/previewLocalDoc/{attId}")
    public void previewLocalDoc(HttpServletRequest request, HttpServletResponse response, @PathVariable("attId") String attId) throws IOException {
        docAttachmentService.previewLocalDoc(request, response, attId);
    }
}
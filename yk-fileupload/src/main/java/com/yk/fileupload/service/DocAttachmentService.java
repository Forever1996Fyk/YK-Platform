package com.yk.fileupload.service;

import com.yk.fileupload.model.pojo.DocAttachment;
import com.yk.fileupload.model.query.DocAttachmentQuery;

import java.util.List;


/**
 * 文档附件Service接口
 *
 * @author YuKai Fan
 * @create 2020-06-10 20:15:50
 */
public interface DocAttachmentService {
    /**
     * 新增文档附件
     * @param docAttachment 文档附件
     * @return
     */
    int insertDocAttachment(DocAttachment docAttachment);

    /**
     * 批量新增文档附件
     * @param list
     */
    int insertDocAttachmentBatch(List<DocAttachment> list);

    /**
     * 更新文档附件
     * @param docAttachment
     * @return
     */
    int updateDocAttachment(DocAttachment docAttachment);

    /**
     * 根据id删除文档附件
     * @param id
     * @return
     */
    int deleteDocAttachmentById(String id);

    /**
     * 批量删除文档附件
     * @param ids
     * @return
     */
    int deleteBatchDocAttachmentByIds(List<String> ids);

    /**
     * 根据id真删除文档附件
     * @param id
     * @return
     */
    int deleteDocAttachmentRealById(String id);

    /**
     * 批量真删除文档附件
     * @param ids
     * @return
     */
    int deleteBatchDocAttachmentRealByIds(List<String> ids);

    /**
     * 根据id获取文档附件
     * @param id
     * @return
     */
    DocAttachment getDocAttachmentById(String id);

    /**
     * 查询文档附件集合
     * @param docAttachmentQuery
     * @return
     */
    List<DocAttachment> listDocAttachments(DocAttachmentQuery docAttachmentQuery);

    /**
     * 查询文档附件集合(分页)
     * @param docAttachmentQuery
     * @return
     */
    List<DocAttachment> listDocAttachments(int start, int pageSize, DocAttachmentQuery docAttachmentQuery);

}

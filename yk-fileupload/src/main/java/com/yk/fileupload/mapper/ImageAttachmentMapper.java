package com.yk.fileupload.mapper;

import java.util.List;

import com.yk.fileupload.model.pojo.ImageAttachment;
import com.yk.fileupload.model.query.ImageAttachmentQuery;
import org.apache.ibatis.annotations.Param;

/**
 * 图片附件Mapper接口
 * 
 * @author YuKai Fan
 * @create 2020-06-10 20:15:50
 */
public interface ImageAttachmentMapper {
    /**
     * 新增图片附件
     * @param imageAttachment 图片附件
     * @return
     */
    int insertImageAttachment(ImageAttachment imageAttachment);

    /**
     * 批量新增图片附件
     * @param list
     */
    int insertImageAttachmentBatch(@Param(value = "list") List<ImageAttachment> list);

    /**
     * 更新图片附件
     * @param imageAttachment
     * @return
     */
    int updateImageAttachment(ImageAttachment imageAttachment);

    /**
     * 根据id删除图片附件
     * @param id
     * @return
     */
    int deleteImageAttachmentById(String id);

    /**
     * 批量删除图片附件
     * @param ids
     * @return
     */
    int deleteBatchImageAttachmentByIds(List<String> ids);

    /**
     * 根据id真删除图片附件
     * @param id
     * @return
     */
    int deleteImageAttachmentRealById(String id);

    /**
     * 批量真删除图片附件
     * @param ids
     * @return
     */
    int deleteBatchImageAttachmentRealByIds(List<String> ids);

    /**
     * 根据id获取图片附件
     * @param id
     * @return
     */
    ImageAttachment getImageAttachmentById(String id);

    /**
     * 查询图片附件集合
     * @param imageAttachmentQuery
     * @return
     */
    List<ImageAttachment> listImageAttachments(ImageAttachmentQuery imageAttachmentQuery);
}

package com.yk.fileupload.mapper;

import java.util.List;

import com.yk.fileupload.model.pojo.VideoAttachment;
import com.yk.fileupload.model.query.VideoAttachmentQuery;
import org.apache.ibatis.annotations.Param;

/**
 * 视频附件Mapper接口
 * 
 * @author YuKai Fan
 * @create 2020-06-10 20:15:50
 */
public interface VideoAttachmentMapper {
    /**
     * 新增视频附件
     * @param videoAttachment 视频附件
     * @return
     */
    int insertVideoAttachment(VideoAttachment videoAttachment);

    /**
     * 批量新增视频附件
     * @param list
     */
    int insertVideoAttachmentBatch(@Param(value = "list") List<VideoAttachment> list);

    /**
     * 更新视频附件
     * @param videoAttachment
     * @return
     */
    int updateVideoAttachment(VideoAttachment videoAttachment);

    /**
     * 根据id删除视频附件
     * @param id
     * @return
     */
    int deleteVideoAttachmentById(String id);

    /**
     * 批量删除视频附件
     * @param ids
     * @return
     */
    int deleteBatchVideoAttachmentByIds(List<String> ids);

    /**
     * 根据id真删除视频附件
     * @param id
     * @return
     */
    int deleteVideoAttachmentRealById(String id);

    /**
     * 批量真删除视频附件
     * @param ids
     * @return
     */
    int deleteBatchVideoAttachmentRealByIds(List<String> ids);

    /**
     * 根据id获取视频附件
     * @param id
     * @return
     */
    VideoAttachment getVideoAttachmentById(String id);

    /**
     * 查询视频附件集合
     * @param videoAttachmentQuery
     * @return
     */
    List<VideoAttachment> listVideoAttachments(VideoAttachmentQuery videoAttachmentQuery);
}

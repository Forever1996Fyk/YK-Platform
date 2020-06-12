package com.yk.common.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @program: YK-Platform
 * @description: 基础附件类
 * @author: YuKai Fan
 * @create: 2020-06-10 20:16
 **/
@Data
public class BaseAttachment {
    /** 视频唯一标识 */
    private String id;
    /** 所属id */
    private String ownerId;
    /** 视频属性 */
    private String attachAttr;
    /** 视频文件MD5 */
    private String attachMd5;
    /** 视频文件sha1值 */
    private String attachSha1;
    /** 视频原名 */
    private String attachOriginTitle;
    /** 视频名称 */
    private String attachName;
    /** 视频大小 */
    private Long attachSize;
    /** 视频后缀 */
    private String attachSuffix;
    /** 视频地址 */
    private String attachUrl;
    /** 视频路径 */
    private String attachPath;
    /** 备注 */
    private String remark;
    /** 状态（1正常 2停用 0删除） */
    private Integer status;
    /** 创建者 */
    private String createUserId;
    /** 创建时间 */
    private String createTime;
    /** 更新者 */
    private String updateUserId;
    /** 更新时间 */
    private String updateTime;
    /** 附加位置类型 */
    private String positionType;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("ownerId", getOwnerId())
                .append("attachAttr", getAttachAttr())
                .append("positionType", getPositionType())
                .append("attachMd5", getAttachMd5())
                .append("attachSha1", getAttachSha1())
                .append("attachOriginTitle", getAttachOriginTitle())
                .append("attachName", getAttachName())
                .append("attachSize", getAttachSize())
                .append("attachSuffix", getAttachSuffix())
                .append("attachUrl", getAttachUrl())
                .append("attachPath", getAttachPath())
                .append("remark", getRemark())
                .append("status", getStatus())
                .append("createUserId", getCreateUserId())
                .append("createTime", getCreateTime())
                .append("updateUserId", getUpdateUserId())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
package com.yk.system.model.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import lombok.Data;
import com.yk.common.entity.BaseEntity;

/**
 * 系统访问记录对象 tb_login_info
 * 
 * @author YuKai Fan
 * @create 2020-06-18 11:50:22
 */
@Data
public class LoginInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 访问唯一标识 */
    private String id;
    /** 登录账号 */
    private String loginName;
    /** 登录ip地址 */
    private String ipAddr;
    /** 登录地点 */
    private String loginLocation;
    /** 浏览器类型 */
    private String browser;
    /** 操作系统 */
    private String os;
    /** 提示消息 */
    private String msg;
    /** 登录状态 */
    private Integer status;
    /** 创建者id */
    private String createUserId;
    /** 创建时间 */
    private String createTime;
    /** 更新人id */
    private String updateUserId;
    /** 更新时间 */
    private String updateTime;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("loginName", getLoginName())
            .append("ipAddr", getIpAddr())
            .append("loginLocation", getLoginLocation())
            .append("browser", getBrowser())
            .append("os", getOs())
            .append("msg", getMsg())
            .append("status", getStatus())
            .append("createUserId", getCreateUserId())
            .append("createTime", getCreateTime())
            .append("updateUserId", getUpdateUserId())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

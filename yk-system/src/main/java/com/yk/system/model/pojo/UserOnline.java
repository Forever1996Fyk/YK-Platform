package com.yk.system.model.pojo;

import com.yk.common.enums.OnlineStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import lombok.Data;
import com.yk.common.entity.BaseEntity;

/**
 * 在线用户记录对象 tb_user_online
 * 
 * @author YuKai Fan
 * @create 2020-06-18 20:41:47
 */
@Data
public class UserOnline extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 用户会话id */
    private String sessionId;
    /** 登录账号 */
    private String loginName;
    /** 登录IP地址 */
    private String ipAddr;
    /** 登录地点 */
    private String loginLocation;
    /** 浏览器类型 */
    private String browser;
    /** 操作系统 */
    private String os;
    /** 在线状态 */
    private OnlineStatus status = OnlineStatus.ON_LINE;
    /** session创建时间 */
    private String startTime;
    /** session最后访问时间 */
    private String lastAccessTime;
    /** 超时时间，单位为分钟 */
    private Long expireTime;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sessionId", getSessionId())
            .append("loginName", getLoginName())
            .append("ipAddr", getIpAddr())
            .append("loginLocation", getLoginLocation())
            .append("browser", getBrowser())
            .append("os", getOs())
            .append("status", getStatus())
            .append("startTime", getStartTime())
            .append("lastAccessTime", getLastAccessTime())
            .append("expireTime", getExpireTime())
            .append("remark", getRemark())
            .append("createUserId", getCreateUserId())
            .append("createTime", getCreateTime())
            .append("updateUserId", getUpdateUserId())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

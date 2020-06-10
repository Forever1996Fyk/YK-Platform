package com.yk.framework.shiro.session;

import com.yk.common.enums.OnlineStatus;
import lombok.Data;
import org.apache.shiro.session.mgt.SimpleSession;

/**
 * @program: YK-Platform
 * @description: 在线用户会话
 * @author: YuKai Fan
 * @create: 2020-06-08 11:01
 **/
@Data
public class OnlineSession extends SimpleSession {
    private static final long serialVersionUID = -3710984850483495450L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 登录ip地址
     */
    private String host;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 属性是否改变
     */
    private transient boolean attributeChanged = false;

    /**
     * 在线状态
     */
    private OnlineStatus status = OnlineStatus.ON_LINE;

    public void markAttributeChanged() {
        this.attributeChanged = true;
    }
}
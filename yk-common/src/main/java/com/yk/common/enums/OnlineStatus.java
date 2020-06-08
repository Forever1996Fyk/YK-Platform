package com.yk.common.enums;

/**
 * @program: YK-Platform
 * @description: 用户会话
 * @author: YuKai Fan
 * @create: 2020-06-08 11:15
 **/
public enum OnlineStatus {
    /**
     * 用户状态
     */
    ON_LINE("在线"),
    OFF_LINE("离线"),
    ;

    private final String content;

    OnlineStatus(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
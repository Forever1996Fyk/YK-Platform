package com.yk.common.enums;

/**
 * @program: YK-Platform
 * @description: 文件上传位置类型
 * @author: YuKai Fan
 * @create: 2020-06-12 15:45
 **/
public enum PositionTypeEnum {
    /**
     * 文件位置
     */
    LOCAL("local"),
    FASTDFS("fastdfs"),
    OSS("oss"),
    ;

    private final String content;

    PositionTypeEnum(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
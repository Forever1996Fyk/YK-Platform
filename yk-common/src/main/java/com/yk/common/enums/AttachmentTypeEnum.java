package com.yk.common.enums;

/**
 * @program: YK-Platform
 * @description: 附件类型
 * @author: YuKai Fan
 * @create: 2020-06-11 15:33
 **/
public enum AttachmentTypeEnum {
    /**
     * 图片
     */
    IMAGE("image"),

    /**
     * 视频
     */
    VIDEO("video"),

    /**
     * 文档
     */
    DOCS("docs"),

    ;

    private String value;

    AttachmentTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
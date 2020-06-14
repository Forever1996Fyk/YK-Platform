package com.yk.common.enums;

/**
 * @ClassName AttachmentAttrEnum
 * @Description 附件属性枚举
 * @Author YuKai Fan
 * @Date 2020/6/13 15:30
 * @Version 1.0
 **/
public enum AttachmentAttrEnum {
    /**
     * 用户头像
     */
    USER_AVATAR("user_avatar"),

    ;

    private String value;

    AttachmentAttrEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

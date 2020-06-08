package com.yk.common.exception.user;

/**
 * 用户锁定异常类
 *
 * @author YuKai Fan
 */
public class UserBlockedException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserBlockedException() {
        super(903, null, "用户账号已被锁定");
    }
}

package com.yk.common.exception.user;

/**
 * 用户账号已被删除
 *
 * @author YuKai Fan
 */
public class UserDeleteException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserDeleteException() {
        super(903, null, "用户账号已被删除");
    }
}

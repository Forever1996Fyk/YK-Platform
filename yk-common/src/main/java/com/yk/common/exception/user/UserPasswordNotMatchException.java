package com.yk.common.exception.user;

/**
 * 用户密码不正确或不符合规范异常类
 *
 * @author YuKai Fan
 */
public class UserPasswordNotMatchException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super(903, null, "用户密码不正确");
    }
}

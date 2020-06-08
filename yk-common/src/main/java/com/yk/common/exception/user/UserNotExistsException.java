package com.yk.common.exception.user;

/**
 * @program: YK-Platform
 * @description: 用户不存在异常
 * @author: YuKai Fan
 * @create: 2020-06-08 16:10
 **/
public class UserNotExistsException extends UserException{
    public UserNotExistsException() {
        super(903, null, "用户不存在");
    }
}
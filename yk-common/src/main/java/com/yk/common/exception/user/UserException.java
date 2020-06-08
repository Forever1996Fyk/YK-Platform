package com.yk.common.exception.user;

import com.yk.common.exception.BaseException;

/**
 * @program: YK-Platform
 * @description: 用户异常
 * @author: YuKai Fan
 * @create: 2020-06-08 16:15
 **/
public class UserException extends BaseException {
    private static final long serialVersionUID = 6165746793793668886L;

    public UserException(Integer code, Object[] args, String message) {
        super("user", code, args, message);
    }
}
package com.yk.common.exception;

/**
 * @program: YK-Platform
 * @description: 业务参数异常
 * @author: YuKai Fan
 * @create: 2020-06-08 09:35
 **/
public class ParameterException extends RuntimeException {
    //返回码
    private int code;

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public ParameterException() {

    }
}
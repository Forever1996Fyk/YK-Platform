package com.yk.common.exception;

import lombok.Getter;

/**
 * @program: YK-Platform
 * @description: 基础异常
 * @author: YuKai Fan
 * @create: 2020-06-08 16:12
 **/
@Getter
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -8816999117539441110L;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误信息
     */
    private String message;

    public BaseException(String module, Integer code, Object[] args, String message) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.message = message;
    }

    public BaseException(String module, Integer code, Object[] args) {
        this(module, code, args, null);
    }

    public BaseException(String module, String message) {
        this(module, null, null, message);
    }

    public BaseException(Integer code, Object[] args) {
        this(null, code, args, null);
    }

    public BaseException(String message) {
        this(null, null, null, message);
    }

}
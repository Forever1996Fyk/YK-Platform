package com.yk.common.exception.file;

import com.yk.common.exception.BaseException;

/**
 * @program: YK-Platform
 * @description: 文件异常
 * @author: YuKai Fan
 * @create: 2020-06-10 20:36
 **/
public class FileException extends BaseException {

    public FileException(Integer code, Object[] args, String message) {
        super("file", code, args, message);
    }
}
package com.yk.common.exception.file;

/**
 * @program: YK-Platform
 * @description: oss异常
 * @author: YuKai Fan
 * @create: 2020-06-16 09:12
 **/
public class OssException extends FileException {

    public OssException(String message) {
        super(904, null, message);
    }
}
package com.yk.common.exception.file;

/**
 * @program: YK-Platform
 * @description:
 * @author: YuKai Fan
 * @create: 2020-06-12 13:46
 **/
public class RequestToFileException extends FileException {
    public RequestToFileException() {
        super(904, null, "ServletRequest转File错误");
    }
}
package com.yk.common.exception.file;

/**
 * @program: YK-Platform
 * @description: 文件大小异常
 * @author: YuKai Fan
 * @create: 2020-06-10 20:38
 **/
public class FileSizeException extends FileException {

    public FileSizeException(long fileSize) {
        super(904, new Object[] { fileSize }, "文件大小异常");
    }
}
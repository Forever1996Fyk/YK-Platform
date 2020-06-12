package com.yk.common.exception.file;

/**
 * @program: YK-Platform
 * @description: 文件读取异常
 * @author: YuKai Fan
 * @create: 2020-06-12 14:16
 **/
public class FileReadErrorException extends FileException{

    public FileReadErrorException() {
        super(904, null, "文件读取异常");
    }
}
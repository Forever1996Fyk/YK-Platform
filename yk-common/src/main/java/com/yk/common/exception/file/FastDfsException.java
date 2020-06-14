package com.yk.common.exception.file;

/**
 * @ClassName FastDfsException
 * @Description FastDFS 上传下载时可能出现的一些异常信息
 * @Author YuKai Fan
 * @Date 2020/6/13 22:01
 * @Version 1.0
 **/
public class FastDfsException extends FileException{
    public FastDfsException(String message) {
        super(904, null, message);
    }
}

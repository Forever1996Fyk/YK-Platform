package com.yk.fileupload.config.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @program: YK-Platform
 * @description: 文件配置属性
 * @author: YuKai Fan
 * @create: 2020-06-10 20:53
 **/
@Configuration
@Getter
public class FileProperties {

    /**
     * 文件上传位置
     */
    @Value("${file.localFilePath}")
    private String filePath;

    /**
     * fastDFS文件根地址
     */
    @Value(("${file.fastDFSAddr}"))
    private String fastDFSAddr;
}
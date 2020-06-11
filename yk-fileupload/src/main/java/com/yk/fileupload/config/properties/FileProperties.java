package com.yk.fileupload.config.properties;

import lombok.Getter;
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
    private String filePath = "E:/uploadVideo/";
}
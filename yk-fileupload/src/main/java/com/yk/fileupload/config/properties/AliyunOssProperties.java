package com.yk.fileupload.config.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName AliyunOssProperties
 * @Description 阿里云Oss配置类
 * @Author YuKai Fan
 * @Date 2020/6/15 20:44
 * @Version 1.0
 **/
@Configuration
@Getter
public class AliyunOssProperties {

    private static AliyunOssProperties aliyunOssProperties;

    public static AliyunOssProperties getInstance() {
        return aliyunOssProperties;
    }

    /**
     * 阿里云oss accessKeyId
     */
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    /**
     * 阿里云oss accessKeySecret
     */
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    /**
     * 阿里云oss endpoint 地区
     */
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
}

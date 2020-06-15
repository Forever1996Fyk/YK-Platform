package com.yk.fileupload.util.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.yk.fileupload.config.properties.AliyunOssProperties;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * @ClassName AliyunOssUtil
 * @Description 阿里云Oss工具类
 * @Author YuKai Fan
 * @Date 2020/6/15 20:43
 * @Version 1.0
 **/
@Slf4j
public class AliyunOssUtil {
    /**
     * 基础配置
     */
    private final static String ACCESS_KEY_ID = AliyunOssProperties.getInstance().getAccessKeyId();
    private final static String ACCESS_KEY_SECRET = AliyunOssProperties.getInstance().getAccessKeySecret();
    private final static String ENDPOINT = AliyunOssProperties.getInstance().getEndpoint();

    private static OSS client = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

    /**
     *
     * @param is
     * @return
     */
    public static String simpleUpload(InputStream is) {
        client.putObject(new PutObjectRequest("", "", is));
        return "";
    }
}

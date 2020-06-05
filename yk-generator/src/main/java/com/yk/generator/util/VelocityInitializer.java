package com.yk.generator.util;

import com.yk.common.constant.ComConstants;
import org.apache.velocity.app.Velocity;

import java.util.Properties;

/**
 * @program: YK-Platform
 * @description: Velocity引擎初始化
 * @author: YuKai Fan
 * @create: 2020-06-05 17:09
 **/
public class VelocityInitializer {
    public static void init() {
        Properties p = new Properties();
        try {
            // 加载classpath目录下的vm文件
            p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.ENCODING_DEFAULT, ComConstants.UTF8);
            p.setProperty(Velocity.OUTPUT_ENCODING, ComConstants.UTF8);
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
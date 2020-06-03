package com.yk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @program: YK-Platform
 * @description: YK服务启动
 * @author: YuKai Fan
 * @create: 2020-06-03 08:56
 **/
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan(basePackages = {"com.yk.**.mapper"})
public class YkApplication {

    public static void main(String[] args) {
        SpringApplication.run(YkApplication.class, args);
    }
}
package com.yk.generator.service;

/**
 * @program: YK-Platform
 * @description: 生成代码service
 * @author: YuKai Fan
 * @create: 2020-06-03 15:53
 **/
public interface GenCodeService {
    /**
     * 生成代码
     * @param tableName
     * @return
     */
    byte[] generatorCode(String tableName);
}
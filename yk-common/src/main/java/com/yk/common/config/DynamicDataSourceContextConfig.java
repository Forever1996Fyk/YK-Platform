package com.yk.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: YK-Platform
 * @description: 数据源切换
 * @author: YuKai Fan
 * @create: 2020-06-03 09:32
 **/
public class DynamicDataSourceContextConfig {
    public static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextConfig.class);

    /**
     * 使用ThreadLocal维护变量, ThreadLocal为每个使用该变量的线程提供独立的变量副本
     * 所以每一个线程都可以独立的改变自己的副本, 而不会影响其他线程所对应的副本
     */
    private static final ThreadLocal<String> CONTENT_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源变量
     * @param dsType
     */
    public static void setDataSourceType(String dsType) {
        logger.info("切换到 ==={}=== 数据源", dsType);
        CONTENT_HOLDER.set(dsType);
    }

    /**
     * 获取数据源的变量
     * @return
     */
    public static String getDataSourceType() {
        return CONTENT_HOLDER.get();
    }

    /**
     * 清空数据源变量
     */
    public static void clearDataSourceType() {
        CONTENT_HOLDER.remove();
    }
}
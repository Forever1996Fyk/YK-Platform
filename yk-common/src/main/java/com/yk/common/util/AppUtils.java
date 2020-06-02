package com.yk.common.util;

/**
 * @program: YK-Platform
 * @description: App工具类
 * @author: YuKai Fan
 * @create: 2020-06-02 20:58
 **/
public class AppUtils {

    /**
     * 获取随机全局唯一id
     * @return
     */
    public static String randomId() {
        IdWorker idWorker = new IdWorker(1, 1);
        return String.valueOf(idWorker.nextId());
    }

    /**
     * 随机生成6位数字
     * @return
     */
    public static String getCheckCode() {
        return String.valueOf((int) ((Math.random() * 9.0D + 1.0D) * 100000.0D));
    }
}
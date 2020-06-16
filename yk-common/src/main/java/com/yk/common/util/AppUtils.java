package com.yk.common.util;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * @program: YK-Platform
 * @description: App工具类
 * @author: YuKai Fan
 * @create: 2020-06-02 20:58
 **/
public class AppUtils extends Thread {

    private static List<Long> idList = null;

    /**
     * 获取随机全局唯一id
     * @return
     */
    public static String randomId() {
        return String.valueOf(IdWorker.idWorker().createId());
    }

    public static void main(String[] args) throws InterruptedException {
        idList = Lists.newArrayList();
        final CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 2; i++) {
            AppUtils appUtils = new AppUtils(latch, i);
            appUtils.start();
        }
        Thread.sleep(5000);
        System.out.println(idList);
        System.out.println("去重前ID数量: " + idList.size());
        idList = idList.stream().distinct().collect(Collectors.toList());
        System.out.println("去重后ID数量: " + idList.size());
    }

    private CountDownLatch latch;
    private int num;

    public AppUtils(CountDownLatch latch, int num) {
        this.latch = latch;
        this.num = num;
    }


    @Override
    public void run() {
        latch.countDown();
        try {
            latch.await();
            for (int i = 0; i < 5; i++) {
                long id = IdWorker.idWorker().createId();
                idList.add(id);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 随机生成6位数字
     * @return
     */
    public static String getCheckCode() {
        return String.valueOf((int) ((Math.random() * 9.0D + 1.0D) * 100000.0D));
    }
}
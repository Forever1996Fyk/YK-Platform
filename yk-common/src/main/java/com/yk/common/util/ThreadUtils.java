package com.yk.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ExecutorConfigurationSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program: YK-Platform
 * @description: 线程工具类
 * @author: YuKai Fan
 * @create: 2020-06-11 10:11
 **/
public class ThreadUtils {
    private static final Logger logger = LoggerFactory.getLogger(ThreadUtils.class);

    /**
     * 停止线程池
     * 先使用shutdown, 停止接收新任务并尝试完成所有已存在任务.
     * 如果超时, 则调用shutdownNow, 取消在workQueue中Pending的任务,并中断所有阻塞函数.
     * 如果仍人超時，則強制退出.
     * 另对在shutdown时线程本身被调用中断做了处理.
     * @param pool
     */
    public static void shutdownAdnAwaitTermination(ExecutorService pool) {
        if (pool != null && !pool.isShutdown()) {
            pool.shutdown();
            try {
                if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
                    pool.shutdown();
                    if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
                        logger.info("Pool did not terminate, 线程池还未终止");
                    }
                }
            } catch (InterruptedException e) {
                pool.shutdown();
                Thread.currentThread().interrupt();
            }
        }
    }
}
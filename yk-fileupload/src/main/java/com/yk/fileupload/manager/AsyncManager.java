package com.yk.fileupload.manager;

import com.yk.common.util.SpringUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @program: YK-Platform
 * @description: 异步任务管理器
 * @author: YuKai Fan
 * @create: 2020-06-11 10:04
 **/
public class AsyncManager {

    /**
     * 异步操作任务线程池
     */
    private ThreadPoolTaskExecutor executor = SpringUtils.getBean("threadPoolTaskExecutor");

    /**
     * 单例模式
     */
    private AsyncManager() {}

    private static AsyncManager asyncManager = new AsyncManager();

    public static AsyncManager asyncManager() {
        return asyncManager;
    }

    /**
     * 执行任务
     * @param runnable
     */
    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        executor.shutdown();
    }
}
package com.yk.framework.manager;

import com.yk.common.util.SpringUtils;
import com.yk.common.util.ThreadUtils;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program: YK-Platform
 * @description: 异步任务管理器
 * @author: YuKai Fan
 * @create: 2020-06-18 10:56
 **/
public class AsyncTaskManager {
    /**
     * 操作延迟10毫秒
     */
    private final int OPERATE_DELAY_TIME = 10;

    /**
     * 异步操作任务调度线程池
     */
    private ScheduledExecutorService executor = SpringUtils.getBean("scheduledExecutorService");

    /**
     * 单例模式
     */
    private AsyncTaskManager(){}

    private static AsyncTaskManager asyncManager = new AsyncTaskManager();

    public static AsyncTaskManager asyncManager() {
        return asyncManager;
    }

    /**
     * 执行任务
     * @param task
     */
    public void execute(TimerTask task) {
        executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        ThreadUtils.shutdownAdnAwaitTermination(executor);
    }
}
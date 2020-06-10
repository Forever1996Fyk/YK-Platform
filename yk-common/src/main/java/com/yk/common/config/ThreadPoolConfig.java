package com.yk.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @program: YK-Platform
 * @description: 线程池配置
 * @author: YuKai Fan
 * @create: 2020-06-10 16:35
 **/
@Configuration
public class ThreadPoolConfig {
    /**
     * 核心线程池大小
     */
    private int corePoolSize = 50;

    /**
     * 最大可创建的线程数
     */
    private int maxPoolSize = 200;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private int keepAliveSeconds = 300;

    /**
     * 队列最大长度
     */
    private int queueCapacity = 1000;

    @Bean("threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(maxPoolSize);
        executor.setCorePoolSize(corePoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        // 线程池对拒绝任务的处理策略(无线程可用)
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
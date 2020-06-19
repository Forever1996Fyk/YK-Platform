package com.yk.framework.shiro.web.session;

import com.yk.common.util.ThreadUtils;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program: YK-Platform
 * @description:
 * @author: YuKai Fan
 * @create: 2020-06-18 22:06
 **/
@Component
public class SpringSessionValidationScheduler implements SessionValidationScheduler {
    private static final Logger logger = LoggerFactory.getLogger(SpringSessionValidationScheduler.class);

    public static final long DEFAULT_SESSION_VALIDATION_INTERVAL = DefaultSessionManager.DEFAULT_SESSION_VALIDATION_INTERVAL;

    /**
     * 定时器, 用于处理超时的挂起请求, 也用于连接断开时的重连
     */
    @Autowired
    @Qualifier("scheduledExecutorService")
    private ScheduledExecutorService executorService;

    private volatile boolean enabled = true;

    /**
     * 会话验证管理器
     */
    @Autowired
    @Qualifier("sessionManager")
    @Lazy
    private ValidatingSessionManager sessionManager;

    // 相隔多久检查一次session的有效性，单位毫秒，默认就是10分钟
    @Value("${shiro.session.validationInterval}")
    private long sessionValidationInterval;

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Specifies how frequently (in milliseconds) this Scheduler will call the
     * {@link org.apache.shiro.session.mgt.ValidatingSessionManager#validateSessions()
     * ValidatingSessionManager#validateSessions()} method.
     *
     * <p>
     * Unless this method is called, the default value is {@link #DEFAULT_SESSION_VALIDATION_INTERVAL}.
     *
     * @param sessionValidationInterval
     */
    public void setSessionValidationInterval(long sessionValidationInterval) {
        this.sessionValidationInterval = sessionValidationInterval;
    }

    @Override
    public void enableSessionValidation() {
        enabled = true;

        if (logger.isDebugEnabled()) {
            logger.debug("Scheduling session validation job using Spring Scheduler with "
                    + "session validation interval of [" + sessionValidationInterval + "]ms...");
        }

        try {
            executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    if (enabled) {
                        sessionManager.validateSessions();
                    }
                }
            }, 1000, sessionValidationInterval * 60 * 1000, TimeUnit.MILLISECONDS);

            this.enabled = true;

            if (logger.isDebugEnabled()) {
                logger.debug("Session validation job successfully scheduled with Spring Scheduler.");
            }

        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("Error starting the Spring Scheduler session validation job.  Session validation may not occur.", e);
            }
        }
    }

    @Override
    public void disableSessionValidation() {
        if (logger.isDebugEnabled()) {
            logger.debug("Stopping Spring Scheduler session validation job...");
        }

        if (this.enabled) {
            ThreadUtils.shutdownAndAwaitTermination(executorService);
        }
        this.enabled = false;
    }
}
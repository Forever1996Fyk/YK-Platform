package com.yk.framework.shiro.web.filter;

import com.yk.common.constant.ComConstants;
import com.yk.common.constant.ShiroConstants;
import com.yk.common.util.StringUtils;
import com.yk.framework.manager.AsyncTaskManager;
import com.yk.framework.manager.factory.AsyncTaskFactory;
import com.yk.framework.util.ShiroUtils;
import com.yk.system.model.pojo.SysUser;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Deque;

/**
 * @program: YK-Platform
 * @description: 退出登录过滤器
 * @author: YuKai Fan
 * @create: 2020-06-10 11:25
 **/
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {
    private static final Logger logger = LoggerFactory.getLogger(LogoutFilter.class);

    /**
     * 退出后重定向的地址
     */
    private String loginUrl;

    private Cache<String, Deque<Serializable>> cache;

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        try {
            Subject subject = getSubject(request, response);
            String redirectUrl = getRedirectUrl(request, response, subject);
            SysUser sysUser = ShiroUtils.getCurrentSysUser();
            if (StringUtils.isNotBlank(sysUser)) {
                String userId = sysUser.getId();
                //记录用户退出日志
                AsyncTaskManager.asyncManager().execute(AsyncTaskFactory.recordLoginInfo(sysUser.getUserName(), ComConstants.LOGOUT, "用户退出登录"));
                //清理缓存
                cache.remove(userId);
            }
            // 退出登录
            subject.logout();
            issueRedirect(request, response, redirectUrl);
        } catch (Exception e) {
            logger.error("session在退出的登录异常, 可以安全忽略", e);
        }

        return false;
    }

    @Override
    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject) {
        String loginUrl = getLoginUrl();
        if (StringUtils.isNotBlank(loginUrl)) {
            return loginUrl;
        }
        return super.getRedirectUrl(request, response, subject);
    }

    // 设置Cache的key的前缀
    public void setCacheManager(CacheManager cacheManager) {
        // 必须和ehcache缓存配置中的缓存name一致
        this.cache = cacheManager.getCache(ShiroConstants.SYS_USER_CACHE);
    }
}
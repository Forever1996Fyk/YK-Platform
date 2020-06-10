package com.yk.framework.shiro.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yk.common.constant.ShiroConstants;
import com.yk.common.dto.Result;
import com.yk.common.util.ServletUtils;
import com.yk.framework.util.ShiroUtils;
import com.yk.system.model.pojo.SysUser;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @program: YK-Platform
 * @description: 登录帐号控制过滤器
 * @author: YuKai Fan
 * @create: 2020-06-10 10:32
 **/
public class KickOutSessionFilter extends AccessControlFilter {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 同一个用户最大会话数
     */
    private Integer maxSession = -1;

    /**
     * 踢出之前登录的/之后登录的用户 默认false踢出之前登录的用户
     */
    private Boolean kickOutAfter = false;

    /**
     * 踢出后跳转地址
     */
    private String kickOutUrl;

    private SessionManager sessionManager;
    private Cache<String, Deque<Serializable>> cache;

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated() && !subject.isRemembered() || maxSession == -1) {
            // 如果没有登录或者用户最大会话数为-1, 直接返回true
            return true;
        }
        try {
            Session session = subject.getSession();
            //当前登录用户
            SysUser sysUser = ShiroUtils.getCurrentSysUser();
            String userId = sysUser.getId();
            Serializable sessionId = session.getId();

            //读取缓存用户, 没有就存入
            Deque<Serializable> deque = cache.get(userId);
            if (deque == null) {
                // 初始化队列
                deque = new ArrayDeque<>();
            }

            // 如果队列里没有此sessionId, 且用户没有被踢出 就放入队列
            if (!deque.contains(sessionId) && session.getAttribute("kickOut") == null) {
                // 将sessionId存入队列
                deque.push(sessionId);
                //将用户的sessionId队列缓存
                cache.put(userId, deque);
            }

            //如果队列里的sessionId数超出最大会话数, 开始踢人,
            while (deque.size() > maxSession) {
                Serializable kickOutSessionId;
                // 是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；
                if (kickOutAfter) {
                    // 踢出后者
                    kickOutSessionId = deque.removeFirst();
                } else {
                    // 踢出前者
                    kickOutSessionId = deque.removeLast();
                }
                // 踢出后在更新下缓存队列
                cache.put(userId, deque);

                try {
                    // 获取被踢出的sessionId的session对象
                    Session kickOutSession = sessionManager.getSession(new DefaultSessionKey(kickOutSessionId));
                    if (kickOutSession != null) {
                        // 设置会话的kickOut属性标识踢出
                        kickOutSession.setAttribute("kickOut", true);
                    }
                } catch (Exception e) {
                    // 异常直接选择忽略
                }
            }

            // 如果被踢出了, 直接退出, 重定向到踢出后的地址
            if (session.getAttribute("kickOut") != null && (Boolean) session.getAttribute("kickOut") == true) {
                // 退出登录
                subject.logout();
                saveRequest(request);
                return isAjaxResponse(request, response);
            }
            return true;
        } catch (Exception e) {
            return isAjaxResponse(request, response);
        }
    }

    private boolean isAjaxResponse(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (ServletUtils.isAjaxRequest(req)) {
            Result result = Result.error("您已在别处登录，请您修改密码或重新登录");
            ServletUtils.renderString(res, objectMapper.writeValueAsString(result));
        } else {
            WebUtils.issueRedirect(request, response, kickOutUrl);
        }

        return false;
    }

    public void setMaxSession(Integer maxSession) {
        this.maxSession = maxSession;
    }

    public void setKickOutAfter(Boolean kickOutAfter) {
        this.kickOutAfter = kickOutAfter;
    }

    public void setKickOutUrl(String kickOutUrl) {
        this.kickOutUrl = kickOutUrl;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    // 设置Cache的key的前缀
    public void setCacheManager(CacheManager cacheManager) {
        // 必须和ehcache缓存配置中的缓存name一致
        this.cache = cacheManager.getCache(ShiroConstants.SYS_USER_CACHE);
    }
}
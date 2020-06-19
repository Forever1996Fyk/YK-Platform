package com.yk.framework.shiro.web.filter;

import com.yk.common.constant.ShiroConstants;
import com.yk.common.enums.OnlineStatus;
import com.yk.common.util.StringUtils;
import com.yk.framework.shiro.session.OnlineSession;
import com.yk.framework.shiro.session.OnlineSessionDAO;
import com.yk.framework.util.ShiroUtils;
import com.yk.system.model.pojo.SysUser;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @program: YK-Platform
 * @description: 自定义访问控制
 * @author: YuKai Fan
 * @create: 2020-06-18 21:49
 **/
public class OnlineSessionFilter extends AccessControlFilter {

    /**
     * 强制退出后重定向的地址
     */
    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    @Autowired
    private OnlineSessionDAO onlineSessionDAO;

    /**
     * 标识是否允许访问: mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
     * @param request
     * @param response
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject == null || subject.getSession() == null) {
            return true;
        }
        Session session = onlineSessionDAO.readSession(subject.getSession().getId());
        if (session != null && session instanceof OnlineSession) {
            OnlineSession onlineSession = (OnlineSession) session;
            request.setAttribute(ShiroConstants.ONLINE_SESSION, onlineSession);
            // 把user对象设置进去
            boolean isGuest = StringUtils.isBlank(onlineSession.getUserId()) || onlineSession.getUserId().equals("0");
            if (isGuest) {
                SysUser user = ShiroUtils.getCurrentSysUser();
                if (user != null) {
                    onlineSession.setUserId(user.getId());
                    onlineSession.setUserName(user.getUserName());
                    onlineSession.setAvatar(user.getAvatarUrl());
                    onlineSession.markAttributeChanged();
                }
            }

            if (onlineSession.getStatus().equals(OnlineStatus.OFF_LINE)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject != null) {
            subject.logout();
        }
        saveRequestAndRedirectToLogin(request, response);
        return false;
    }

    /**
     * 跳转到登录页
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        WebUtils.issueRedirect(request, response, loginUrl);
    }
}
package com.yk.framework.shiro.session;

import com.yk.common.util.WebUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: YK-Platform
 * @description: 自定义Session工厂
 * @author: YuKai Fan
 * @create: 2020-06-08 11:19
 **/
@Component
public class OnlineSessionFactory implements SessionFactory {
    @Override
    public Session createSession(SessionContext sessionContext) {
        OnlineSession session = new OnlineSession();
        if (sessionContext != null && sessionContext instanceof WebSessionContext) {
            WebSessionContext webSessionContext = (WebSessionContext) sessionContext;
            HttpServletRequest request = (HttpServletRequest) webSessionContext.getServletRequest();
            if (request != null) {
                UserAgent userAgent = UserAgent.parseUserAgentString(WebUtils.getHttpServletRequest().getHeader("User-Agent"));
                //获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                //获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                session.setHost(WebUtils.getRemoteAddress(request));
                session.setBrowser(browser);
                session.setOs(os);
            }
        }
        return session;
    }
}
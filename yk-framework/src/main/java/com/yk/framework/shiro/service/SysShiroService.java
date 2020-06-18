package com.yk.framework.shiro.service;

import com.yk.common.util.StringUtils;
import com.yk.common.util.TimeUtils;
import com.yk.framework.shiro.session.OnlineSession;
import com.yk.system.model.pojo.UserOnline;
import com.yk.system.service.UserOnlineService;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.ParseException;

/**
 * @program: YK-Platform
 * @description: session会话操作处理
 * @author: YuKai Fan
 * @create: 2020-06-18 20:58
 **/
@Component
public class SysShiroService {

    @Autowired
    private UserOnlineService userOnlineService;

    /**
     * 删除会话
     * @param onlineSession
     */
    public void deleteSession(OnlineSession onlineSession) {
        userOnlineService.deleteUserOnlineById(String.valueOf(onlineSession.getId()));
    }

    /**
     * 获取会话信息
     * @param sessionId
     * @return
     */
    public Session getSession(Serializable sessionId) throws ParseException {
        UserOnline userOnline = userOnlineService.getUserOnlineById(String.valueOf(sessionId));
        return StringUtils.isBlank(userOnline) ? null : createSession(userOnline);
    }

    private Session createSession(UserOnline userOnline) throws ParseException {
        OnlineSession onlineSession = new OnlineSession();
        if (userOnline != null) {
            onlineSession.setId(userOnline.getSessionId());
            onlineSession.setHost(userOnline.getIpAddr());
            onlineSession.setBrowser(userOnline.getBrowser());
            onlineSession.setOs(userOnline.getOs());
            onlineSession.setUserName(userOnline.getLoginName());
            onlineSession.setStartTimestamp(TimeUtils.parseTimeToDate(userOnline.getStartTime()));
            onlineSession.setLastAccessTime(TimeUtils.parseTimeToDate(userOnline.getLastAccessTime()));
            onlineSession.setTimeout(userOnline.getExpireTime());
        }

        return onlineSession;
    }
}
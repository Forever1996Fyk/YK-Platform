package com.yk.framework.shiro.web.session;

import com.google.common.collect.Lists;
import com.yk.common.constant.ShiroConstants;
import com.yk.common.util.BeanUtils;
import com.yk.common.util.SpringUtils;
import com.yk.common.util.StringUtils;
import com.yk.common.util.TimeUtils;
import com.yk.framework.shiro.session.OnlineSession;
import com.yk.system.model.pojo.UserOnline;
import com.yk.system.service.UserOnlineService;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @program: YK-Platform
 * @description: 如果会话的属性修改了 就标识其修改了 然后方便 OnlineSessionDao同步
 * @author: YuKai Fan
 * @create: 2020-06-10 09:05
 **/
public class OnlineWebSessionManager extends DefaultWebSessionManager {
    private static final Logger logger = LoggerFactory.getLogger(OnlineWebSessionManager.class);

    @Override
    public void setAttribute(SessionKey sessionKey, Object attributeKey, Object value) throws InvalidSessionException {
        super.setAttribute(sessionKey, attributeKey, value);
        if (value != null && needMarkAttributeChanged(attributeKey)) {
            OnlineSession session = getOnlineSession(sessionKey);
            session.markAttributeChanged();
        }
    }

    @Override
    public Object removeAttribute(SessionKey sessionKey, Object attributeKey) throws InvalidSessionException {
        Object remove = super.removeAttribute(sessionKey, attributeKey);
        if (remove != null) {
            OnlineSession session = getOnlineSession(sessionKey);
            session.markAttributeChanged();
        }
        return remove;
    }

    @Override
    protected Collection<Session> getActiveSessions() {
        throw new UnsupportedOperationException("getActiveSessions method not supported");
    }

    /**
     * 验证session是否有效 用于删除过期session
     */
    @Override
    public void validateSessions() {
        if (logger.isInfoEnabled()) {
            logger.info("invalidation sessions...");
        }

        int invalidCount = 0;

        int timeout = (int) this.getGlobalSessionTimeout();
        Date expiredDate = TimeUtils.addMilliseconds(new Date(), 0 - timeout);
        UserOnlineService userOnlineService = SpringUtils.getBean(UserOnlineService.class);
        List<UserOnline> userOnlineList = userOnlineService.listUserOnlineByExpired(TimeUtils.parseTime(expiredDate));
        // 批量过期删除
        List<String> needOfflineIdList = Lists.newArrayList();
        for (UserOnline userOnline : userOnlineList) {
            try {
                SessionKey key = new DefaultSessionKey(userOnline.getSessionId());
                Session session = retrieveSession(key);
                if (session != null) {
                    throw new InvalidSessionException();
                }
            } catch (InvalidSessionException e) {
                if (logger.isDebugEnabled()) {
                    boolean expired = e instanceof ExpiredSessionException;
                    String msg = StringUtils.format("Invalidated session with id [{}]", (expired ? "(expired)" : "stopped"));
                    logger.debug(msg);
                }
            }
            invalidCount++;
            needOfflineIdList.add(userOnline.getSessionId());
        }

        if (!CollectionUtils.isEmpty(needOfflineIdList)) {
            try {
                userOnlineService.deleteBatchUserOnlineRealByIds(needOfflineIdList);
            } catch (Exception e) {
                logger.error("批量删除数据中的session错误, [{}]", e);
            }
        }

        if (logger.isInfoEnabled()) {
            String msg = "结束验证session";
            if (invalidCount > 0) {
                msg += StringUtils.format("[{}], sessions 已经被停止!", invalidCount);
            } else {
                msg += "没有sessions被停止";
            }
            logger.info(msg);
        }
    }


    private OnlineSession getOnlineSession(SessionKey sessionKey) {
        OnlineSession session = null;
        Object object = doGetSession(sessionKey);
        if (StringUtils.isNotBlank(object)) {
            session = new OnlineSession();
            BeanUtils.copyBeanProp(session, object);
        }
        return session;
    }

    //是否是需要标记的会话
    private boolean needMarkAttributeChanged(Object attributeKey) {
        if (attributeKey == null) {
            return false;
        }
        String attributeKeyStr = attributeKey.toString();
        //flash属性没必要持久化
        if (attributeKeyStr.startsWith("org.springframework")) {
            return false;
        }

        if (attributeKeyStr.startsWith("javax.servlet")) {
            return false;
        }

        if (attributeKeyStr.equals(ShiroConstants.CURRENT_USERNAME)) {
            return false;
        }

        return true;
    }
}
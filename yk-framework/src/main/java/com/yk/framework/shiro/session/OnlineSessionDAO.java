package com.yk.framework.shiro.session;

import com.yk.common.util.StringUtils;
import com.yk.framework.manager.AsyncTaskManager;
import com.yk.framework.manager.factory.AsyncTaskFactory;
import com.yk.framework.shiro.service.SysShiroService;
import lombok.SneakyThrows;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: YK-Platform
 * @description: 针对自定义的ShiroSession的db操作
 * @author: YuKai Fan
 * @create: 2020-06-08 11:17
 **/
public class OnlineSessionDAO extends EnterpriseCacheSessionDAO {

    /**
     * 同步session到数据库的周期 单位为毫秒(默认1分钟)
     */
    @Value("${shiro.session.dbSyncPeriod}")
    private int dbSyncPeriod;

    /**
     * 上次同步数据库的时间戳
     */
    private static final String LAST_SYNC_DB_TIMESTAMP = OnlineSessionDAO.class.getName() + "LAST_SYNC_DB_TIMESTAMP";

    @Autowired
    private SysShiroService sysShiroService;

    public OnlineSessionDAO() {
        super();
    }

    public OnlineSessionDAO(long expireTime) {
        super();
    }

    /**
     * 根据会话id获取会话
     * @param sessionId
     * @return
     */
    @SneakyThrows
    @Override
    protected Session doReadSession(Serializable sessionId) {
        return sysShiroService.getSession(sessionId);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        super.update(session);
    }

    /**
     * 更新会话, (最后访问时间/停止会话/设置超时时间/设置移除属性等会调用)
     * @param onlineSession
     */
    public void syncToDb(OnlineSession onlineSession) {
        Date lastSyncTimestamp = (Date) onlineSession.getAttribute(LAST_SYNC_DB_TIMESTAMP);
        if (lastSyncTimestamp != null) {
            boolean needSync = true;
            long deltaTime = onlineSession.getLastAccessTime().getTime() - lastSyncTimestamp.getTime();
            if (deltaTime < dbSyncPeriod * 60 * 1000) {
                // 时间差不足 无需同步
                needSync = false;
            }
            // 判断是否访客
            boolean isGuest = StringUtils.isBlank(onlineSession.getUserId()) || onlineSession.getUserId().equals("0");

            // session 数据变更 同步
            if (!isGuest && onlineSession.isAttributeChanged()) {
                needSync = true;
            }

            if (!needSync) {
                return;
            }
        }
        // 更新上次同步数据库时间
        onlineSession.setAttribute(LAST_SYNC_DB_TIMESTAMP, onlineSession.getLastAccessTime());
        // 更新完后 重置标识
        if (onlineSession.isAttributeChanged()) {
            onlineSession.resetAttributeChanged();
        }
        AsyncTaskManager.asyncManager().execute(AsyncTaskFactory.syncSessionToDb(onlineSession));
    }
}
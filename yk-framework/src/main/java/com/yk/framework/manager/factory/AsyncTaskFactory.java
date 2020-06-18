package com.yk.framework.manager.factory;

import com.yk.common.constant.ComConstants;
import com.yk.common.util.*;
import com.yk.framework.util.LogUtils;
import com.yk.framework.util.ShiroUtils;
import com.yk.system.model.pojo.ActionLog;
import com.yk.system.model.pojo.LoginInfo;
import com.yk.system.service.ActionLogService;
import com.yk.system.service.LoginInfoService;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * @program: YK-Platform
 * @description: 异步任务工厂类
 * @author: YuKai Fan
 * @create: 2020-06-18 10:59
 **/
public class AsyncTaskFactory {
    private static final Logger logger = LoggerFactory.getLogger("sys_user");

    /**
     * 记录登录日志信息
     * @param userName 用户名
     * @param status 状态
     * @param message 消息
     * @param args
     * @return
     */
    public static TimerTask recordLoginInfo(final String userName, final String status, final String message, final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getHttpServletRequest().getHeader("User-Agent"));
        final String ip = ShiroUtils.getCurrentIp();
        return new TimerTask() {
            @Override
            public void run() {
                String address = AddressUtils.getRealAddressByIp(ip);
                StringBuilder sb = new StringBuilder();
                sb.append(LogUtils.getBlock(ip));
                sb.append(address);
                sb.append(LogUtils.getBlock(userName));
                sb.append(LogUtils.getBlock(status));
                sb.append(LogUtils.getBlock(message));

                // 打印信息到日志
                logger.info(sb.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                LoginInfo loginInfo = new LoginInfo();
                loginInfo.setLoginName(userName);
                loginInfo.setIpAddr(ip);
                loginInfo.setLoginLocation(address);
                loginInfo.setBrowser(browser);
                loginInfo.setOs(os);
                loginInfo.setMsg(message);
                loginInfo.setCreateTime(TimeUtils.getCurrentDatetime());
                loginInfo.setUpdateTime(TimeUtils.getCurrentDatetime());
                // 日志状态
                if (StringUtils.equalsAny(status, ComConstants.LOGIN_SUCCESS, ComConstants.LOGOUT, ComConstants.REGISTER)) {
                    loginInfo.setStatus(1);
                } else if (ComConstants.LOGIN_FAIL.equals(status)) {
                    loginInfo.setStatus(0);
                }
                // 插入数据
                SpringUtils.getBean(LoginInfoService.class).insertLoginInfo(loginInfo);
            }
        };
    }

    /**
     * 操作日志记录
     * @param actionLog
     * @return
     */
    public static TimerTask recordActionLog(final ActionLog actionLog) {
        return new TimerTask() {
            @Override
            public void run() {
                actionLog.setActionLocation(AddressUtils.getRealAddressByIp(actionLog.getIpAddr()));
                actionLog.setActionTime(TimeUtils.getCurrentDatetime());
                SpringUtils.getBean(ActionLogService.class).insertActionLog(actionLog);
            }
        };
    }
}
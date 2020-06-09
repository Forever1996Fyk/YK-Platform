package com.yk.framework.util;

import com.yk.common.util.StringUtils;
import com.yk.framework.shiro.realm.UserRealm;
import com.yk.system.model.pojo.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * @program: YK-Platform
 * @description: shiro工具类
 * @author: YuKai Fan
 * @create: 2020-06-08 09:47
 **/
public class ShiroUtils {

    /**
     * 获取登录主体
     * @return
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前Session
     * @return
     */
    public static Session getSession() {
        return getSubject().getSession();
    }

    /**
     * 获取当前登录用户信息
     * @return
     */
    public static SysUser getCurrentSysUser() {
        SysUser sysUser = null;
        Object obj = getSubject().getPrincipal();
        if (StringUtils.isNotBlank(obj)) {
            sysUser = (SysUser) obj;
        }
        return sysUser;
    }

    /**
     * 重新设置系统用户
     * @param sysUser
     */
    public static void setSysUser(SysUser sysUser) {
        Subject subject = getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        String realmName = principals.getRealmNames().iterator().next();
        SimplePrincipalCollection simplePrincipalCollection = new SimplePrincipalCollection(sysUser, realmName);
        //重新加载Principal
        subject.runAs(simplePrincipalCollection);
    }

    /**
     * 获取当前登录id
     * @return
     */
    public static String getCurrentUserId() {
        return getCurrentSysUser().getId();
    }

    /**
     * 获取当前登录ip
     * @return
     */
    public static String getCurrentIp() {
        return getSession().getHost();
    }

    /**
     * 生成随机盐
     * @return
     */
    public static String randomSalt() {
        SecureRandomNumberGenerator generator = new SecureRandomNumberGenerator();
        String hex = generator.nextBytes(3).toHex();
        return hex;
    }

    public static void clearCachedAuthorizationInfo() {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        UserRealm realm = (UserRealm) rsm.getRealms().iterator().next();
        realm.clearCachedAuthorizationInfo();
    }
}
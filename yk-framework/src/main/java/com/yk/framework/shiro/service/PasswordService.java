package com.yk.framework.shiro.service;

import com.yk.common.constant.ShiroConstants;
import com.yk.common.exception.user.UserPasswordNotMatchException;
import com.yk.system.model.pojo.SysUser;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: YK-Platform
 * @description: 密码操作服务
 * @author: YuKai Fan
 * @create: 2020-06-03 14:55
 **/
@Component
public class PasswordService {
    @Autowired
    private CacheManager cacheManager;

    /**
     * 登录记录
     */
    private Cache<String, AtomicInteger> loginRecordCache;

    @PostConstruct
    public void init() {
        loginRecordCache = cacheManager.getCache(ShiroConstants.LOGIN_RECORD_CACHE);
    }

    /**
     * 验证登录并校验密码
     *
     * @param user
     * @param password
     */
    public void validate(SysUser user, String password) {
        //校验登录次数 todo
//        String account = user.getAccount();
//        AtomicInteger retryCount = loginRecordCache.get(account);
//        if (retryCount == null) {
//            retryCount = new AtomicInteger(0);
//            loginRecordCache.put(account, retryCount);
//        }
        if (!matches(user, password)) {
            throw new UserPasswordNotMatchException();
        } else {
            //清除登录次数记录
        }
    }

    /**
     * 密码匹配
     *
     * @param user
     * @param newPassword
     * @return
     */
    public boolean matches(SysUser user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getUserName(), newPassword, user.getSalt()));
    }

    /**
     * 密码加盐加密
     *
     * @param userName
     * @param password
     * @param salt
     * @return
     */
    public String encryptPassword(String userName, String password, String salt) {
        return new Md5Hash(userName + password + salt).toHex();
    }
}
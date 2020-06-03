package com.yk.framework.shiro.service;

import com.yk.system.model.pojo.SysUser;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Component;

/**
 * @program: YK-Platform
 * @description: 密码操作服务
 * @author: YuKai Fan
 * @create: 2020-06-03 14:55
 **/
@Component
public class PasswordService {

    /**
     * 验证登录并校验密码
     * @param user
     * @param password
     */
    public void validate(SysUser user, String password) {
        //校验登录次数 todo

        if (!matches(user, password)) {

        } else {
            //清楚登录次数记录
        }
    }

    /**
     * 密码匹配
     * @param user
     * @param newPassword
     * @return
     */
    public boolean matches(SysUser user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getUserName(), newPassword, user.getSalt()));
    }

    /**
     * 密码加盐加密
     * @param userName
     * @param password
     * @param salt
     * @return
     */
    public String encryptPassword(String userName, String password, String salt) {
        return new Md5Hash(userName + password + salt).toHex();
    }
}
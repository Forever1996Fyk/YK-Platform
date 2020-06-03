package com.yk.framework.shiro.service;

import com.yk.common.util.StringUtils;
import com.yk.system.model.pojo.SysUser;
import com.yk.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: YK-Platform
 * @description: 登录服务类
 * @author: YuKai Fan
 * @create: 2020-06-03 14:50
 **/
@Component
public class LoginService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private PasswordService passwordService;

    public SysUser login(String userName, String password) {
        //验证码校验 todo
        //用户名密码为空
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {

        }

        //查询用户信息
        SysUser user = sysUserService.getSysUserByUserName(userName);
        //校验登录方式 手机号, 邮箱 todo
        if (user == null) {

        }
        passwordService.validate(user, password);

        //更新登录记录 todo

        return user;
    }
}
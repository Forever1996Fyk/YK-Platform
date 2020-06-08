package com.yk.framework.shiro.service;

import com.yk.common.constant.UserConstants;
import com.yk.common.exception.ParameterException;
import com.yk.common.exception.user.UserBlockedException;
import com.yk.common.exception.user.UserDeleteException;
import com.yk.common.exception.user.UserNotExistsException;
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

        //用户名密码为空 错误
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            //记录日志 todo
            throw new ParameterException("用户名或者密码不能为空");
        }

        //密码不在指定范围内 错误 todo
        //用户名不在指定范围内 错误 todo


        //查询用户信息
        SysUser user = sysUserService.getSysUserByAccount(userName);
        //校验登录方式 手机号, 邮箱
        if (user == null && StringUtils.verifyPhoneNumber(userName)) {
            user = sysUserService.getSysUserByPhoneNumber(userName);
        }

        if (user == null && StringUtils.verifyEmail(userName)) {
            user = sysUserService.getSysUserByEmail(userName);
        }

        if (user == null) {
            throw new UserNotExistsException();
        }

        if (UserConstants.ACCOUNT_DELETED.equals(user.getStatus())) {
            //用户已被删除
            throw new UserDeleteException();
        }

        if (UserConstants.ACCOUNT_DISABLED.equals(user.getStatus())) {
            //用户已被禁用
            throw new UserBlockedException();
        }
        passwordService.validate(user, password);

        //更新登录记录 todo

        return user;
    }
}
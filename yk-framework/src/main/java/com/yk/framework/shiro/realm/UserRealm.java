package com.yk.framework.shiro.realm;

import com.yk.common.util.StringUtils;
import com.yk.framework.shiro.service.LoginService;
import com.yk.system.model.pojo.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: YK-Platform
 * @description: 自定义Realm 用户认证授权
 * @author: YuKai Fan
 * @create: 2020-06-03 14:48
 **/
public class UserRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private LoginService loginService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = "";
        if (StringUtils.isNotBlank(token.getPassword())) {
            password = new String(token.getPassword());
        }

        SysUser sysUser;
        try {
            sysUser = loginService.login(username, password);
        } catch (Exception e) {
            logger.error(StringUtils.format("对用户[{}]进行登录验证..验证未通过{}", username, e.getMessage()));
            throw new AuthenticationException(e.getMessage(), e);
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(sysUser, password, getName());
        return simpleAuthenticationInfo;
    }
}
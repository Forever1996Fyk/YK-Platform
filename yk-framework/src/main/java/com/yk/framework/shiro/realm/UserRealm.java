package com.yk.framework.shiro.realm;

import com.yk.common.exception.user.UserBlockedException;
import com.yk.common.exception.user.UserNotExistsException;
import com.yk.common.exception.user.UserPasswordNotMatchException;
import com.yk.common.util.StringUtils;
import com.yk.framework.shiro.service.LoginService;
import com.yk.framework.util.ShiroUtils;
import com.yk.system.model.pojo.SysUser;
import com.yk.system.service.SysMenuService;
import com.yk.system.service.SysRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

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
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser sysUser = ShiroUtils.getCurrentSysUser();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (sysUser.isAdmin()) {
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        } else {
            //角色列表
            Set<String> roles = sysRoleService.listRoleCodes(sysUser.getId());
            //菜单列表
            Set<String> menus = sysMenuService.listPermsByUserId(sysUser.getId());
            info.setRoles(roles);
            info.setStringPermissions(menus);
        }
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
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
        } catch (UserNotExistsException e) {
            throw new UnknownAccountException(e.getMessage(), e);
        } catch (UserPasswordNotMatchException e) {
            throw new IncorrectCredentialsException(e.getMessage(), e);
        } catch (UserBlockedException e) {
            throw new LockedAccountException(e.getMessage(), e);
        } catch (Exception e) {
            logger.error(StringUtils.format("对用户[{}]进行登录验证..验证未通过{}", username, e.getMessage()));
            throw new AuthenticationException(e.getMessage(), e);
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(sysUser, password, getName());
        return simpleAuthenticationInfo;
    }

    /**
     * 清楚缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
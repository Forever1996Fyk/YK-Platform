package com.yk.web.controller.system;

import com.yk.common.dto.LoginVo;
import com.yk.common.dto.Result;
import com.yk.common.util.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: YK-Platform
 * @description: 登录controller
 * @author: YuKai Fan
 * @create: 2020-06-03 11:51
 **/
@RestController
@RequestMapping("/api/system")
public class SysLoginController {

    /**
     * 用户登录
     * @param loginVo
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo) {
        UsernamePasswordToken token = new UsernamePasswordToken(loginVo.getUsername(), loginVo.getPassword(), loginVo.getRememberMe());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return Result.success("登录成功");
        } catch (AuthenticationException e) {
            String msg = StringUtils.isNotEmpty(e.getMessage())?e.getMessage():"用户名或者密码错误";
            return Result.error(msg);
        }
    }
}
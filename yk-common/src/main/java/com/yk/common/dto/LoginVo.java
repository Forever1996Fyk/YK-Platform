package com.yk.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: YK-Platform
 * @description: 登录实体
 * @author: YuKai Fan
 * @create: 2020-06-03 14:09
 **/
@Data
public class LoginVo implements Serializable {
    private static final long serialVersionUID = -8019398488087650316L;
    //用户名
    private String username;
    //密码
    private String password;
    //记住我
    private Boolean rememberMe = true;
    //账号
    private String account;
}
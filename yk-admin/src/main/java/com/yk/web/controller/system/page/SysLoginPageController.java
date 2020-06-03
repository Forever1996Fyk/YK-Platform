package com.yk.web.controller.system.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: YK-Platform
 * @description:
 * @author: YuKai Fan
 * @create: 2020-06-03 11:51
 **/
@Controller
public class SysLoginPageController {

    /**
     * 登录页面
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
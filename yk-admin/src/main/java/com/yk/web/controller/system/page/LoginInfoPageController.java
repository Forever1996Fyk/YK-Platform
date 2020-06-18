package com.yk.web.controller.system.page;

import com.yk.system.service.LoginInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: YK-Platform
 * @description: 系统访问记录页面跳转controller
 * @author: YuKai Fan
 * @create: 2020-06-02 21:54
 **/
@Controller
@RequestMapping("/system/loginInfo")
public class LoginInfoPageController {
    private String prefix = "monitor/loginInfo";
    @Autowired
    private LoginInfoService loginInfoService;

    /**
     * 跳转系统访问记录首页
     * @return
     */
    @GetMapping()
    @RequiresPermissions("system:loginInfo:view")
    public String loginInfo() {
        return prefix + "/loginInfo";
    }

    /**
     * 跳转新增系统访问记录页面
     * @return
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 跳转修改系统访问记录页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        model.addAttribute("loginInfo", loginInfoService.getLoginInfoById(id));
        return prefix + "/edit";
    }

}
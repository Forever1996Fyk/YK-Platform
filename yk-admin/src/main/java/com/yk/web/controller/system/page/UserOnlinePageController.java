package com.yk.web.controller.system.page;

import com.yk.system.service.UserOnlineService;
import com.yk.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * @program: YK-Platform
 * @description: 在线用户记录页面跳转controller
 * @author: YuKai Fan
 * @create: 2020-06-02 21:54
 **/
@Controller
@RequestMapping("/system/userOnline")
public class UserOnlinePageController {
    private String prefix = "monitor/online";
    @Autowired
    private UserOnlineService userOnlineService;

    /**
     * 跳转在线用户记录首页
     * @return
     */
    @GetMapping()
    @RequiresPermissions("system:userOnline:view")
    public String userOnline() {
        return prefix + "/userOnline";
    }

    /**
     * 跳转新增在线用户记录页面
     * @return
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 跳转修改在线用户记录页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        model.addAttribute("userOnline", userOnlineService.getUserOnlineById(id));
        return prefix + "/edit";
    }

}
package com.yk.web.controller.system.page;

import com.yk.framework.util.ShiroUtils;
import com.yk.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName SysProfilePageController
 * @Description TODO
 * @Author YuKai Fan
 * @Date 2020/6/13 15:14
 * @Version 1.0
 **/
@Controller
@RequestMapping("/system/user/profile")
public class SysProfilePageController {
    private String prefix = "system/user/profile";

    @Autowired
    private SysUserService sysUserService;

    /**
     * 跳转到个人信息页面
     *
     * @param model
     * @return java.lang.String
     * @author YuKai Fan
     * @date 2020/6/13 15:15
     */
    @GetMapping()
    public String profile(Model model) {
        model.addAttribute("user", ShiroUtils.getCurrentSysUser());
        return prefix + "/profile";
    }

    /**
     * 跳转到修改头像页面
     */
    @GetMapping("/avatar")
    public String avatar(Model model) {
        model.addAttribute("user", ShiroUtils.getCurrentSysUser());
        return prefix + "/avatar";
    }
}

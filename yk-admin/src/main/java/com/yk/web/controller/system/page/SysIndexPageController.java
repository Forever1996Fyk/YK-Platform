package com.yk.web.controller.system.page;

import com.yk.framework.util.ShiroUtils;
import com.yk.system.model.pojo.SysMenu;
import com.yk.system.model.pojo.SysUser;
import com.yk.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @program: YK-Platform
 * @description: 首页controller
 * @author: YuKai Fan
 * @create: 2020-06-03 14:05
 **/
@Controller
public class SysIndexPageController {
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 跳转到首页
     * @return
     */
    @GetMapping("/index")
    public String index(Model model) {

        SysUser sysUser = ShiroUtils.getCurrentSysUser();
        List<SysMenu> menus = sysMenuService.listSysMenusByUser(sysUser);
        model.addAttribute("menus", menus);
        model.addAttribute("user", sysUser);
        return "index";
    }
}
package com.yk.web.controller.system.page;

import com.yk.system.model.pojo.SysMenu;
import com.yk.system.service.SysMenuService;
import com.yk.common.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: YK-Platform
 * @description: 系统菜单页面跳转controller
 * @author: YuKai Fan
 * @create: 2020-06-02 21:54
 **/
@Controller
@RequestMapping("/system/menu")
public class SysMenuPageController {
    private String prefix = "system/menu" ;
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 跳转系统菜单首页
     *
     * @return
     */
    @GetMapping()
    @RequiresPermissions("system:menu:view")
    public String menu() {
        return prefix + "/menu" ;
    }

    /**
     * 跳转新增系统菜单页面
     *
     * @return
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") String parentId, Model model) {
        SysMenu sysMenu;
        if (!"0".equals(parentId)) {
            sysMenu = sysMenuService.getSysMenuById(parentId);
        } else {
            sysMenu = new SysMenu();
            sysMenu.setId("0");
            sysMenu.setMenuName("主目录");
        }

        model.addAttribute("menu", sysMenu);
        return prefix + "/add" ;
    }

    /**
     * 跳转修改系统菜单页面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        model.addAttribute("sysMenu", sysMenuService.getSysMenuById(id));
        return prefix + "/edit" ;
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree/{id}")
    public String selectMenuTree(@PathVariable("id") String id, Model model) {
        model.addAttribute("menu", sysMenuService.getSysMenuById(id));
        return prefix + "/tree" ;
    }

    /**
     * 选择菜单图标
     */
    @GetMapping("/icon")
    public String icon() {
        return prefix + "/icon";
    }

}
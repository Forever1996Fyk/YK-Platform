package com.yk.web.controller.system.page;

import com.yk.system.model.query.SysRoleQuery;
import com.yk.system.service.SysRoleService;
import com.yk.system.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: YK-Platform
 * @description: 系统用户页面跳转controller
 * @author: YuKai Fan
 * @create: 2020-06-02 21:54
 **/
@Controller
@RequestMapping("/system/user")
public class SysUserPageController {
    private String prefix = "system/user";
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 跳转系统用户首页
     * @return
     */
    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user() {
        return prefix + "/user";
    }

    /**
     * 跳转新增系统用户页面
     * @return
     */
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("roles", sysRoleService.listSysRoles(new SysRoleQuery()));
        return prefix + "/add";
    }

    /**
     * 跳转修改系统用户页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        model.addAttribute("sysUser", sysUserService.getSysUserById(id));
        model.addAttribute("roles", sysRoleService.listSysRolesByUserId(id));
        return prefix + "/edit";
    }
}
package com.yk.web.controller.system.page;

import com.yk.system.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: YK-Platform
 * @description: 系统角色页面跳转controller
 * @author: YuKai Fan
 * @create: 2020-06-02 21:54
 **/
@Controller
@RequestMapping("/system/role")
public class SysRolePageController {
    private String prefix = "system/role";
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 跳转系统角色首页
     * @return
     */
    @GetMapping()
    @RequiresPermissions("system:role:view")
    public String role() {
        return prefix + "/role";
    }

    /**
     * 跳转新增系统角色页面
     * @return
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 跳转修改系统角色页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        model.addAttribute("sysRole", sysRoleService.getSysRoleById(id));
        return prefix + "/edit";
    }

    /**
     * 跳转到分配用户
     * @param roleId
     * @param model
     * @return
     */
    @GetMapping("/authUser/{roleId}")
    public String authUser(@PathVariable("roleId") String roleId, Model model) {
        model.addAttribute("role", sysRoleService.getSysRoleById(roleId));
        return prefix + "/authUser";
    }

    /**
     * 跳转到分配用户
     * @param roleId
     * @param model
     * @return
     */
    @GetMapping("/authUser/selectUser/{roleId}")
    public String addAuthUser(@PathVariable("roleId") String roleId, Model model) {
        model.addAttribute("role", sysRoleService.getSysRoleById(roleId));
        return prefix + "/selectUser";
    }

}
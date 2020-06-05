package com.yk.web.controller.system;

import com.yk.common.dto.Result;
import com.yk.system.model.pojo.SysRole;
import com.yk.system.model.query.SysRoleQuery;
import com.yk.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: YK-Platform
 * @description: 系统角色controller
 * @author: YuKai Fan
 * @create: 2020-06-05 22:28:42
 **/
@RestController
@RequestMapping("/api/system/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;


    /**
     * 新增系统角色
     * @param sysRole
     * @return
     */
    @PostMapping("/addSysRole")
    public Result addSysRole(@RequestBody SysRole sysRole) {
        return Result.response(sysRoleService.insertSysRole(sysRole));
    }

    /**
     * 修改系统角色
     * @param sysRole
     * @return
     */
    @PutMapping("/editSysRole")
    public Result editSysRole(@RequestBody SysRole sysRole) {
        return Result.response(sysRoleService.updateSysRole(sysRole));
    }

}
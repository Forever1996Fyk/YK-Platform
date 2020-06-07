package com.yk.web.controller.system;

import com.yk.common.dto.Result;
import com.yk.system.model.pojo.SysRole;
import com.yk.system.model.query.SysRoleQuery;
import com.yk.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.yk.common.dto.DataTablesViewPage;

/**
 * @program: YK-Platform
 * @description: 系统角色controller
 * @author: YuKai Fan
 * @create: 2020-06-06 22:42:30
 **/
@RestController
@RequestMapping("/api/system/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 获取系统角色集合
     * @param start
     * @param pageSize
     * @param sysRoleQuery
     * @return
     */
    @GetMapping("/list")
    public Result listSysRoles(@RequestParam(value = "start", defaultValue = "0") int start,
                               @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                               SysRoleQuery sysRoleQuery) {
        List<SysRole> list = sysRoleService.listSysRoles(start, pageSize, sysRoleQuery);
        return Result.success("获取成功", new DataTablesViewPage<>(list));
    }

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
    /**
     * 根据id删除系统角色
     * @param id
     * @return
     */
    @DeleteMapping("/deleteSysRoleById/{id}")
    public Result deleteSysRoleById(@PathVariable("id") String id) {
        return Result.response(sysRoleService.deleteSysRoleById(id));
    }

    /**
     * 批量删除系统角色
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteBatchSysRoleByIds/{ids}")
    public Result deleteBatchSysRoleByIds(@PathVariable("ids") List<String> ids) {
        return Result.response(sysRoleService.deleteBatchSysRoleByIds(ids));
    }

}
package com.yk.web.controller.system;

import com.yk.common.dto.Result;
import com.yk.system.model.pojo.SysRole;
import com.yk.system.model.pojo.SysUser;
import com.yk.system.model.pojo.UserRole;
import com.yk.system.model.query.SysRoleQuery;
import com.yk.system.model.query.SysUserQuery;
import com.yk.system.service.SysRoleService;
import com.yk.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @Autowired
    private SysUserService sysUserService;

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

    /**
     * 校验角色名称
     */
    @GetMapping("/checkRoleNameUnique")
    @ResponseBody
    public String checkRoleNameUnique(SysRole role)
    {
        return sysRoleService.checkRoleNameUnique(role);
    }

    /**
     * 校验角色权限
     */
    @GetMapping("/checkRoleCodeUnique")
    @ResponseBody
    public String checkRoleCodeUnique(SysRole role)
    {
        return sysRoleService.checkRoleCodeUnique(role);
    }

    /**
     * 获取已分配用户角色列表
     * @param start
     * @param pageSize
     * @param sysUserQuery
     * @return
     */
    @GetMapping("/authUser/listAllocatedUsers")
    public Result listAllocatedUsers(@RequestParam(value = "start", defaultValue = "0") int start,
                                     @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                     SysUserQuery sysUserQuery) {

        List<SysUser> list = sysUserService.listAllocatedUsers(start, pageSize, sysUserQuery);
        return Result.success(new DataTablesViewPage<>(list));
    }

    /**
     * 获取已分配用户角色列表
     * @param start
     * @param pageSize
     * @param sysUserQuery
     * @return
     */
    @GetMapping("/authUser/listUnallocatedUsers")
    public Result listUnallocatedUsers(@RequestParam(value = "start", defaultValue = "0") int start,
                                     @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                     SysUserQuery sysUserQuery) {

        List<SysUser> list = sysUserService.listUnallocatedUsers(start, pageSize, sysUserQuery);
        return Result.success(new DataTablesViewPage<>(list));
    }

    /**
     * 批量选择用户授权
     * @param userRole
     * @return
     */
    @PostMapping("/authUser/selectUserAuthRole")
    public Result selectUserAuthRole(@RequestBody UserRole userRole) {
        return Result.response(sysRoleService.insertAuthUsers(userRole.getRoleId(), userRole.getUserId()));
    }

    /**
     * 批量取消用户授权
     * @param roleId
     * @param userId
     * @return
     */
    @DeleteMapping("/authUser/cancelAuthUsers/{roleId}/{userId}")
    public Result cancelAuthUsers(@PathVariable("roleId") String roleId, @PathVariable("userId") String userId) {
        return Result.response(sysRoleService.deleteAuthUsers(roleId, userId));
    }
}
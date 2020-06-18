package com.yk.web.controller.system;

import com.yk.common.annotation.ActionLog;
import com.yk.common.constant.UserConstants;
import com.yk.common.dto.DataTablesViewPage;
import com.yk.common.dto.Result;
import com.yk.common.enums.LogTypeEnum;
import com.yk.common.exception.ParameterException;
import com.yk.common.util.AssertUtils;
import com.yk.framework.shiro.service.PasswordService;
import com.yk.framework.util.ShiroUtils;
import com.yk.system.model.pojo.SysUser;
import com.yk.system.model.query.SysUserQuery;
import com.yk.system.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: YK-Platform
 * @description: 系统用户controller
 * @author: YuKai Fan
 * @create: 2020-06-02 21:40
 **/
@RestController
@RequestMapping("/api/system/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private PasswordService passwordService;

    /**
     * 获取系统用户集合
     * @param start
     * @param pageSize
     * @param sysUserQuery
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("system:user:list")
    public Result listSysUsers(@RequestParam(value = "start", defaultValue = "0") int start,
                               @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                               SysUserQuery sysUserQuery) {
        List<SysUser> list = sysUserService.listSysUsers(start, pageSize, sysUserQuery);
        return Result.success("获取成功", new DataTablesViewPage<>(list));
    }

    /**
     * 新增系统用户
     * @param sysUser
     * @return
     */
    @ActionLog(name = "用户管理", logType = LogTypeEnum.INSERT)
    @PostMapping("/addSysUser")
    @RequiresPermissions("system:user:add")
    public Result addSysUser(@RequestBody SysUser sysUser) {
        sysUser.setSalt(ShiroUtils.randomSalt());
        sysUser.setPassword(UserConstants.DEFAULT_PASSWORD);
        sysUser.setAccount(sysUser.getPhone());
        sysUser.setPassword(passwordService.encryptPassword(sysUser.getUserName(), sysUser.getPassword(), sysUser.getSalt()));
        return Result.response(sysUserService.insertSysUser(sysUser));
    }

    /**
     * 修改系统用户
     * @param sysUser
     * @return
     */
    @ActionLog(name = "用户管理", logType = LogTypeEnum.UPDATE)
    @PutMapping("/editSysUser")
    @RequiresPermissions("system:user:edit")
    public Result editSysUser(@RequestBody SysUser sysUser) {
        if (!UserConstants.ADMIN_USER_ID.equals(ShiroUtils.getCurrentUserId()) && UserConstants.ADMIN_USER_ID.equals(sysUser.getId())) {
            throw new ParameterException("无法修改超级管理员账号!");
        }
        return Result.response(sysUserService.updateSysUser(sysUser));
    }

    /**
     * 根据id删除系统用户
     * @param id
     * @return
     */
    @ActionLog(name = "用户管理", logType = LogTypeEnum.DELETE)
    @DeleteMapping("/deleteSysUserById/{id}")
    @RequiresPermissions("system:user:delete")
    public Result deleteSysUserById(@PathVariable("id") String id) {
        AssertUtils.checkUserAllowed(id);
        return Result.response(sysUserService.deleteSysUserById(id));
    }

    /**
     * 批量删除系统用户
     * @param ids
     * @return
     */
    @ActionLog(name = "用户管理", logType = LogTypeEnum.DELETE)
    @RequiresPermissions("system:user:delete")
    @DeleteMapping("/deleteBatchSysUserByIds/{ids}")
    public Result deleteBatchSysUserByIds(@PathVariable("ids") List<String> ids) {
        ids.forEach(id -> AssertUtils.checkUserAllowed(id));
        return Result.response(sysUserService.deleteBatchSysUserByIds(ids));
    }
}
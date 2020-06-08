package com.yk.web.controller.system;

import com.yk.common.dto.DataTablesViewPage;
import com.yk.common.dto.Result;
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
    @PostMapping("/addSysUser")
    public Result addSysUser(@RequestBody SysUser sysUser) {
        sysUser.setSalt(ShiroUtils.randomSalt());
        sysUser.setPassword(passwordService.encryptPassword(sysUser.getUserName(), sysUser.getPassword(), sysUser.getSalt()));
        return Result.response(sysUserService.insertSysUser(sysUser));
    }

    /**
     * 修改系统用户
     * @param sysUser
     * @return
     */
    @PutMapping("/editSysUser")
    public Result editSysUser(@RequestBody SysUser sysUser) {
        return Result.response(sysUserService.updateSysUser(sysUser));
    }

    /**
     * 根据id删除系统用户
     * @param id
     * @return
     */
    @DeleteMapping("/deleteSysUserById/{id}")
    public Result deleteSysUserById(@PathVariable("id") String id) {
        return Result.response(sysUserService.deleteSysUserById(id));
    }

    /**
     * 批量删除系统用户
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteBatchSysUserByIds/{ids}")
    public Result deleteBatchSysUserByIds(@PathVariable("ids") List<String> ids) {
        return Result.response(sysUserService.deleteBatchSysUserByIds(ids));
    }
}
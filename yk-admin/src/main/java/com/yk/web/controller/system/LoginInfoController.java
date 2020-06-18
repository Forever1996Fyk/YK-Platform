package com.yk.web.controller.system;

import com.yk.common.dto.Result;
import com.yk.system.model.pojo.LoginInfo;
import com.yk.system.model.query.LoginInfoQuery;
import com.yk.system.service.LoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.List;
import com.yk.common.dto.DataTablesViewPage;

/**
 * @program: YK-Platform
 * @description: 系统访问记录controller
 * @author: YuKai Fan
 * @create: 2020-06-18 11:56:01
 **/
@RestController
@RequestMapping("/api/system/loginInfo")
public class LoginInfoController {
    @Autowired
    private LoginInfoService loginInfoService;

    /**
     * 获取系统访问记录集合
     * @param start
     * @param pageSize
     * @param loginInfoQuery
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("system:loginInfo:list")
    public Result listLoginInfos(@RequestParam(value = "start", defaultValue = "0") int start,
                               @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                               LoginInfoQuery loginInfoQuery) {
        List<LoginInfo> list = loginInfoService.listLoginInfos(start, pageSize, loginInfoQuery);
        return Result.success("获取成功", new DataTablesViewPage<>(list));
    }

    /**
     * 新增系统访问记录
     * @param loginInfo
     * @return
     */
    @PostMapping("/addLoginInfo")
    @RequiresPermissions("system:loginInfo:add")
    public Result addLoginInfo(@RequestBody LoginInfo loginInfo) {
        return Result.response(loginInfoService.insertLoginInfo(loginInfo));
    }

    /**
     * 修改系统访问记录
     * @param loginInfo
     * @return
     */
    @PutMapping("/editLoginInfo")
    @RequiresPermissions("system:loginInfo:edit")
    public Result editLoginInfo(@RequestBody LoginInfo loginInfo) {
        return Result.response(loginInfoService.updateLoginInfo(loginInfo));
    }
    /**
     * 根据id删除系统访问记录
     * @param id
     * @return
     */
    @DeleteMapping("/deleteLoginInfoById/{id}")
    @RequiresPermissions("system:loginInfo:delete")
    public Result deleteLoginInfoById(@PathVariable("id") String id) {
        return Result.response(loginInfoService.deleteLoginInfoById(id));
    }

    /**
     * 批量删除系统访问记录
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteBatchLoginInfoByIds/{ids}")
    @RequiresPermissions("system:loginInfo:delete")
    public Result deleteBatchLoginInfoByIds(@PathVariable("ids") List<String> ids) {
        return Result.response(loginInfoService.deleteBatchLoginInfoByIds(ids));
    }

}
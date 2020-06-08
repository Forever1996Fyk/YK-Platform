package com.yk.web.controller.system;

import com.yk.common.dto.Result;
import com.yk.common.entity.Ztree;
import com.yk.framework.util.ShiroUtils;
import com.yk.system.model.pojo.SysMenu;
import com.yk.system.model.pojo.SysRole;
import com.yk.system.model.query.SysMenuQuery;
import com.yk.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.yk.common.dto.DataTablesViewPage;

/**
 * @program: YK-Platform
 * @description: 系统菜单controller
 * @author: YuKai Fan
 * @create: 2020-06-07 11:26:54
 **/
@RestController
@RequestMapping("/api/system/menu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 获取系统菜单集合
     *
     * @param start
     * @param pageSize
     * @param sysMenuQuery
     * @return
     */
    @GetMapping("/list")
    public Result listSysMenus(@RequestParam(value = "start", defaultValue = "0") int start,
                               @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                               SysMenuQuery sysMenuQuery) {
        List<SysMenu> list = sysMenuService.listSysMenus(start, pageSize, sysMenuQuery);
        return Result.success("获取成功", new DataTablesViewPage<>(list));
    }

    /**
     * 获取系统菜单集合
     *
     * @param sysMenuQuery
     * @return
     */
    @GetMapping("/listNoPage")
    public List<SysMenu> listSysMenus(SysMenuQuery sysMenuQuery) {
        //获取当前登录人id
        List<SysMenu> list = sysMenuService.listSysMenus(sysMenuQuery, ShiroUtils.getCurrentUserId());
        return list;
    }

    /**
     * 新增系统菜单
     *
     * @param sysMenu
     * @return
     */
    @PostMapping("/addSysMenu")
    public Result addSysMenu(@RequestBody SysMenu sysMenu) {
        return Result.response(sysMenuService.insertSysMenu(sysMenu));
    }

    /**
     * 修改系统菜单
     *
     * @param sysMenu
     * @return
     */
    @PutMapping("/editSysMenu")
    public Result editSysMenu(@RequestBody SysMenu sysMenu) {
        return Result.response(sysMenuService.updateSysMenu(sysMenu));
    }

    /**
     * 根据id删除系统菜单
     *
     * @param id
     * @return
     */
    @DeleteMapping("/deleteSysMenuById/{id}")
    public Result deleteSysMenuById(@PathVariable("id") String id) {
        return Result.response(sysMenuService.deleteSysMenuById(id));
    }

    /**
     * 批量删除系统菜单
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteBatchSysMenuByIds/{ids}")
    public Result deleteBatchSysMenuByIds(@PathVariable("ids") List<String> ids) {
        return Result.response(sysMenuService.deleteBatchSysMenuByIds(ids));
    }

    /**
     * 加载所有菜单列表树
     *
     * @return
     */
    @GetMapping("/menuTreeData")
    public Result menuTreeData() {
        //获取当前登录人id todo
        List<Ztree> ztrees = sysMenuService.menuTreeData(ShiroUtils.getCurrentUserId());
        return Result.success(ztrees);
    }

    /**
     * 加载角色菜单列表树
     */
    @GetMapping("/roleMenuTreeData")
    @ResponseBody
    public Result roleMenuTreeData(SysRole role) {
        String userId = ShiroUtils.getCurrentUserId();
        List<Ztree> ztrees = sysMenuService.roleMenuTreeData(role, userId);
        return Result.success(ztrees);
    }

    /**
     * 校验菜单名称
     *
     * @param menu
     * @return
     */
    @GetMapping("/checkMenuNameUnique")
    public String checkMenuNameUnique(SysMenu menu) {
        String result = sysMenuService.checkMenuNameUnique(menu);
        return result;
    }

}
package com.yk.system.service;

import com.yk.system.model.pojo.SysMenu;
import com.yk.system.model.pojo.SysUser;

import java.util.List;

/**
 * @program: YK-Platform
 * @description: 系统菜单service
 * @author: YuKai Fan
 * @create: 2020-06-02 21:33
 **/
public interface SysMenuService {
    /**
     * 新增系统菜单
     * @param sysUser
     * @return
     */
    int insertSysMenu(SysMenu sysUser);

    /**
     * 批量新增用户系统
     * @param list
     */
    int insertSysMenuBatch(List<SysMenu> list);

    /**
     * 更新系统菜单
     * @param sysUser
     * @return
     */
    int updateSysMenu(SysMenu sysUser);

    /**
     * 根据id删除系统菜单
     * @param id
     * @return
     */
    int deleteSysMenuById(String id);

    /**
     * 批量删除系统菜单
     * @param ids
     * @return
     */
    int deleteBatchSysMenuByIds(List<String> ids);

    /**
     * 根据id真删除系统菜单
     * @param id
     * @return
     */
    int deleteSysMenuRealById(String id);

    /**
     * 批量真删除系统菜单
     * @param list
     * @return
     */
    int deleteBatchSysMenuRealByIds(List<String> list);

    /**
     * 根据id获取系统菜单
     * @param id
     * @return
     */
    SysMenu getSysMenuById(String id);

    /**
     * 根据用户获得菜单
     * @param sysUser
     * @return
     */
    List<SysMenu> listSysMenusByUser(SysUser sysUser);
}
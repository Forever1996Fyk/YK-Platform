package com.yk.system.mapper;
import com.yk.system.model.pojo.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: YK-Platform
 * @description: 系统菜单mapper
 * @author: YuKai Fan
 * @create: 2020-06-02 20:59
 **/
public interface SysMenuMapper {

    /**
     * 新增系统用户
     * @param sysUser
     * @return
     */
    int insertSysMenu(SysMenu sysUser);

    /**
     * 批量新增用户系统
     * @param list
     */
    int insertSysMenuBatch(@Param(value = "list") List<SysMenu> list);

    /**
     * 更新系统用户
     * @param sysUser
     * @return
     */
    int updateSysMenu(SysMenu sysUser);

    /**
     * 根据id删除系统用户
     * @param id
     * @return
     */
    int deleteSysMenuById(String id);

    /**
     * 批量删除系统用户
     * @param ids
     * @return
     */
    int deleteBatchSysMenuByIds(List<String> ids);

    /**
     * 根据id真删除系统用户
     * @param id
     * @return
     */
    int deleteSysMenuRealById(String id);

    /**
     * 批量真删除系统用户
     * @param ids
     * @return
     */
    int deleteBatchSysMenuRealByIds(List<String> ids);

    /**
     * 根据id获取系统用户
     * @param id
     * @return
     */
    SysMenu getSysMenuById(String id);

    /**
     * 获取所有的菜单
     * @return
     */
    List<SysMenu> listMenuAll();
}
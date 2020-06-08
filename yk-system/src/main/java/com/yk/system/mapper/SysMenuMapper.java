package com.yk.system.mapper;
import com.yk.system.model.pojo.SysMenu;
import com.yk.system.model.query.SysMenuQuery;
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
     * @param sysMenu
     * @return
     */
    int insertSysMenu(SysMenu sysMenu);

    /**
     * 批量新增用户系统
     * @param list
     */
    int insertSysMenuBatch(@Param(value = "list") List<SysMenu> list);

    /**
     * 更新系统用户
     * @param sysMenu
     * @return
     */
    int updateSysMenu(SysMenu sysMenu);

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
    int deleteBatchSysMenuByIds(@Param("list") List<String> ids);

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
    int deleteBatchSysMenuRealByIds(@Param("list") List<String> ids);

    /**
     * 根据id获取系统用户
     * @param id
     * @return
     */
    SysMenu getSysMenuById(String id);

    /**
     * 获取所有的菜单(不包括按钮)
     * @return
     */
    List<SysMenu> listMenuNotButtonAll();

    /**
     * 根据用户id获取菜单(不包括按钮)
     * @param userId
     * @return
     */
    List<SysMenu> listMenuNotButtonByUserId(String userId);

    /**
     * 获取菜单集合
     * @param sysMenuQuery
     * @return
     */
    List<SysMenu> listSysMenus(SysMenuQuery sysMenuQuery);

    /**
     * 根据用户id获取菜单集合
     * @param sysMenuQuery
     * @return
     */
    List<SysMenu> listSysMenusByUserId(SysMenuQuery sysMenuQuery);

    /**
     * 检查菜单名称是否唯一
     * @param menuName
     * @param parentId
     * @return
     */
    SysMenu checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") String parentId);

    /**
     * 根据userId获取权限列表
     * @param userId
     * @return
     */
    List<String> listPermsByUserId(String userId);

    /**
     * 根据角色id获取菜单id
     * @param roleId
     * @return
     */
    List<String> listMenusByRoleId(String roleId);
}
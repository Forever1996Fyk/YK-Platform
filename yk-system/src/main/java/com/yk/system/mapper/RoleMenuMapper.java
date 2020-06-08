package com.yk.system.mapper;

import java.util.List;
import com.yk.system.model.pojo.RoleMenu;
import org.apache.ibatis.annotations.Param;

/**
 * 角色菜单关联Mapper接口
 * 
 * @author YuKai Fan
 * @create 2020-06-08 20:13:24
 */
public interface RoleMenuMapper {
    /**
     * 新增角色菜单关联
     * @param roleMenu 角色菜单关联
     * @return
     */
    int insertRoleMenu(RoleMenu roleMenu);

    /**
     * 批量新增角色菜单关联
     * @param list
     */
    int insertRoleMenuBatch(@Param(value = "list") List<RoleMenu> list);

    /**
     * 根据roleId真删除角色菜单关联
     * @param roleId
     * @return
     */
    int deleteRoleMenuRealByRoleId(String roleId);

    /**
     * 批量真删除角色菜单关联
     * @param ids
     * @return
     */
    int deleteBatchRoleMenuRealByIds(List<String> ids);

    /**
     * 根据id获取角色菜单关联
     * @param id
     * @return
     */
    RoleMenu getRoleMenuById(String id);
}

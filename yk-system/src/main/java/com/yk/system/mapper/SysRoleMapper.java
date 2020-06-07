package com.yk.system.mapper;

import java.util.List;
import com.yk.system.model.pojo.SysRole;
import com.yk.system.model.query.SysRoleQuery;
import org.apache.ibatis.annotations.Param;

/**
 * 系统角色Mapper接口
 * 
 * @author YuKai Fan
 * @create 2020-06-06 22:42:30
 */
public interface SysRoleMapper {
    /**
     * 新增系统角色
     * @param sysRole 系统角色
     * @return
     */
    int insertSysRole(SysRole sysRole);

    /**
     * 批量新增系统角色
     * @param list
     */
    int insertSysRoleBatch(@Param(value = "list") List<SysRole> list);

    /**
     * 更新系统角色
     * @param sysRole
     * @return
     */
    int updateSysRole(SysRole sysRole);

    /**
     * 根据id删除系统角色
     * @param id
     * @return
     */
    int deleteSysRoleById(String id);

    /**
     * 批量删除系统角色
     * @param ids
     * @return
     */
    int deleteBatchSysRoleByIds(List<String> ids);

    /**
     * 根据id真删除系统角色
     * @param id
     * @return
     */
    int deleteSysRoleRealById(String id);

    /**
     * 批量真删除系统角色
     * @param ids
     * @return
     */
    int deleteBatchSysRoleRealByIds(List<String> ids);

    /**
     * 根据id获取系统角色
     * @param id
     * @return
     */
    SysRole getSysRoleById(String id);

    /**
     * 查询系统角色集合
     * @param sysRoleQuery
     * @return
     */
    List<SysRole> listSysRoles(SysRoleQuery sysRoleQuery);
}

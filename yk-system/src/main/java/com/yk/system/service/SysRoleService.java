package com.yk.system.service;

import java.util.List;
import java.util.Set;

import com.yk.system.model.pojo.SysRole;
import com.yk.system.model.query.SysRoleQuery;

/**
 * 系统角色Service接口
 *
 * @author YuKai Fan
 * @create 2020-06-06 22:42:30
 */
public interface SysRoleService {
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
    int insertSysRoleBatch(List<SysRole> list);

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

    /**
     * 查询系统角色集合(分页)
     * @param sysRoleQuery
     * @return
     */
    List<SysRole> listSysRoles(int start, int pageSize, SysRoleQuery sysRoleQuery);

    /**
     * 根据userId获取角色Code
     * @param userId
     * @return
     */
    Set<String> listRoleCodes(String userId);

    /**
     * 检查角色名称的唯一性
     * @param role
     * @return
     */
    String checkRoleNameUnique(SysRole role);

    /**
     * 检查角色code唯一性
     * @param role
     * @return
     */
    String checkRoleCodeUnique(SysRole role);

    /**
     * 批量选择用户授权
     * @param roleId
     * @param userIds
     * @return
     */
    int insertAuthUsers(String roleId, String userIds);

    /**
     * 批量取消用户授权
     * @param roleId
     * @param userIds
     * @return
     */
    int deleteAuthUsers(String roleId, String userIds);
}

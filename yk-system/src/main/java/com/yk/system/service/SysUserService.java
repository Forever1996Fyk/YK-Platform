package com.yk.system.service;

import com.yk.system.model.pojo.SysUser;
import com.yk.system.model.query.SysUserQuery;

import java.io.Serializable;
import java.util.List;

/**
 * @program: YK-Platform
 * @description: 系统用户service
 * @author: YuKai Fan
 * @create: 2020-06-02 21:33
 **/
public interface SysUserService {
    /**
     * 新增系统用户
     * @param sysUser
     * @return
     */
    int insertSysUser(SysUser sysUser);

    /**
     * 批量新增用户系统
     * @param list
     */
    int insertSysUserBatch(List<SysUser> list);

    /**
     * 更新系统用户
     * @param sysUser
     * @return
     */
    int updateSysUser(SysUser sysUser);

    /**
     * 根据id删除系统用户
     * @param id
     * @return
     */
    int deleteSysUserById(String id);

    /**
     * 批量删除系统用户
     * @param ids
     * @return
     */
    int deleteBatchSysUserByIds(List<String> ids);

    /**
     * 根据id真删除系统用户
     * @param id
     * @return
     */
    int deleteSysUserRealById(String id);

    /**
     * 批量真删除系统用户
     * @param list
     * @return
     */
    int deleteBatchSysUserRealByIds(List<String> list);

    /**
     * 根据id获取系统用户
     * @param id
     * @return
     */
    SysUser getSysUserById(String id);

    /**
     * 查询系统用户集合
     * @param sysUserQuery
     * @return
     */
    List<SysUser> listSysUsers(SysUserQuery sysUserQuery);

    /**
     * 查询系统用户集合(分页)
     * @param sysUserQuery
     * @return
     */
    List<SysUser> listSysUsers(int start, int pageSize, SysUserQuery sysUserQuery);

    /**
     * 根据账号获取用户信息
     * @param account
     * @return
     */
    SysUser getSysUserByAccount(String account);

    /**
     * 根据用户名获取用户信息
     * @param userName
     * @return
     */
    SysUser getSysUserByUserName(String userName);

    /**
     * 根据手机号获取用户信息
     * @param phone
     * @return
     */
    SysUser getSysUserByPhoneNumber(String phone);

    /**
     * 根据邮箱获取用户信息
     * @param email
     * @return
     */
    SysUser getSysUserByEmail(String email);

    /**
     * 获取已分配用户角色列表
     * @param start
     * @param pageSize
     * @param sysUserQuery
     * @return
     */
    List<SysUser> listAllocatedUsers(int start, int pageSize, SysUserQuery sysUserQuery);

    /**
     * 获取未分配的用户列表
     * @param start
     * @param pageSize
     * @param sysUserQuery
     * @return
     */
    List<SysUser> listUnallocatedUsers(int start, int pageSize, Serializable sysUserQuery);
}
package com.yk.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yk.common.util.AppUtils;
import com.yk.common.util.StringUtils;
import com.yk.common.util.TimeUtils;
import com.yk.system.mapper.SysUserMapper;
import com.yk.system.mapper.UserRoleMapper;
import com.yk.system.model.pojo.SysUser;
import com.yk.system.model.pojo.UserRole;
import com.yk.system.model.query.SysUserQuery;
import com.yk.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @program: YK-Platform
 * @description: 系统用户service实现类
 * @author: YuKai Fan
 * @create: 2020-06-02 21:34
 **/
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public int insertSysUser(SysUser sysUser) {
        sysUser.setId(AppUtils.randomId());
        sysUser.setStatus(1);
        sysUser.setCreateTime(TimeUtils.getCurrentDatetime());
        sysUser.setUpdateTime(TimeUtils.getCurrentDatetime());
        int row = sysUserMapper.insertSysUser(sysUser);

        //添加用户角色关系
        insertUserRoles(sysUser);
        return row;
    }

    private void insertUserRoles(SysUser sysUser) {
        if (StringUtils.isBlank(sysUser.getRoleId())) {
            return;
        }
        String[] roleIds = sysUser.getRoleId().split(",");
        List<UserRole> list = Lists.newArrayList();
        for (String roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(sysUser.getId());
            userRole.setRoleId(roleId);
            list.add(userRole);
        }
        userRoleMapper.insertUserRoleBatch(list);
    }

    @Override
    public int insertSysUserBatch(List<SysUser> list) {
        return sysUserMapper.insertSysUserBatch(list);
    }

    @Override
    public int updateSysUser(SysUser sysUser) {
        //先删除用户角色关联
        userRoleMapper.deleteUserRoleRealByUserId(sysUser.getId());
        // 在新增用户角色关系
        insertUserRoles(sysUser);
        sysUser.setUpdateTime(TimeUtils.getCurrentDatetime());
        return sysUserMapper.updateSysUser(sysUser);
    }

    @Override
    public int deleteSysUserById(String id) {
        return sysUserMapper.deleteSysUserById(id);
    }

    @Override
    public int deleteBatchSysUserByIds(List<String> ids) {
        return sysUserMapper.deleteBatchSysUserByIds(ids);
    }

    @Override
    public int deleteSysUserRealById(String id) {
        return sysUserMapper.deleteSysUserRealById(id);
    }

    @Override
    public int deleteBatchSysUserRealByIds(List<String> list) {
        return sysUserMapper.deleteBatchSysUserRealByIds(list);
    }

    @Override
    public SysUser getSysUserById(String id) {
        return sysUserMapper.getSysUserById(id);
    }

    @Override
    public List<SysUser> listSysUsers(SysUserQuery sysUserQuery) {
        return sysUserMapper.listSysUsers(sysUserQuery);
    }

    @Override
    public List<SysUser> listSysUsers(int start, int pageSize, SysUserQuery sysUserQuery) {
        PageHelper.startPage(start, pageSize);
        return this.listSysUsers(sysUserQuery);
    }

    @Override
    public SysUser getSysUserByAccount(String account) {
        return sysUserMapper.getSysUserByAccount(account);
    }

    @Override
    public SysUser getSysUserByUserName(String userName) {
        return sysUserMapper.getSysUserByUserName(userName);
    }

    @Override
    public SysUser getSysUserByPhoneNumber(String phone) {
        return sysUserMapper.getSysUserByPhoneNumber(phone);
    }

    @Override
    public SysUser getSysUserByEmail(String email) {
        return sysUserMapper.getSysUserByEmail(email);
    }

    @Override
    public List<SysUser> listAllocatedUsers(int start, int pageSize, SysUserQuery sysUserQuery) {
        PageHelper.startPage(start, pageSize);
        return sysUserMapper.listAllocatedUsers(sysUserQuery);
    }

    @Override
    public List<SysUser> listUnallocatedUsers(int start, int pageSize, Serializable sysUserQuery) {
        PageHelper.startPage(start, pageSize);
        return sysUserMapper.listUnallocatedUsers(sysUserQuery);
    }
}
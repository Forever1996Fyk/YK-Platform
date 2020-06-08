package com.yk.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Sets;
import com.yk.common.util.AppUtils;
import com.yk.common.util.StringUtils;
import com.yk.common.util.TimeUtils;
import com.yk.system.mapper.SysRoleMapper;
import com.yk.system.model.pojo.SysRole;
import com.yk.system.model.query.SysRoleQuery;
import com.yk.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;


/**
 * @program: YK-Platform
 * @description: 系统角色service实现类
 * @author: YuKai Fan
 * @create: 2020-06-06 22:42:30
 **/
@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public int insertSysRole(SysRole sysRole) {
        sysRole.setId(AppUtils.randomId());
        sysRole.setStatus(1);
        sysRole.setCreateTime(TimeUtils.getCurrentDatetime());
        sysRole.setUpdateTime(TimeUtils.getCurrentDatetime());
        return sysRoleMapper.insertSysRole(sysRole);
    }

    @Override
    public int insertSysRoleBatch(List<SysRole> list) {
        return sysRoleMapper.insertSysRoleBatch(list);
    }

    @Override
    public int updateSysRole(SysRole sysRole) {
        sysRole.setUpdateTime(TimeUtils.getCurrentDatetime());
        return sysRoleMapper.updateSysRole(sysRole);
    }

    @Override
    public int deleteSysRoleById(String id) {
        return sysRoleMapper.deleteSysRoleById(id);
    }

    @Override
    public int deleteBatchSysRoleByIds(List<String> ids) {
        return sysRoleMapper.deleteBatchSysRoleByIds(ids);
    }

    @Override
    public int deleteSysRoleRealById(String id) {
        return sysRoleMapper.deleteSysRoleRealById(id);
    }

    @Override
    public int deleteBatchSysRoleRealByIds(List<String> list) {
        return sysRoleMapper.deleteBatchSysRoleRealByIds(list);
    }

    @Override
    public SysRole getSysRoleById(String id) {
        return sysRoleMapper.getSysRoleById(id);
    }

    @Override
    public List<SysRole> listSysRoles(SysRoleQuery sysRoleQuery) {
        return sysRoleMapper.listSysRoles(sysRoleQuery);
    }

    @Override
    public List<SysRole> listSysRoles(int start, int pageSize, SysRoleQuery sysRoleQuery) {
        PageHelper.startPage(start, pageSize);
        return this.listSysRoles(sysRoleQuery);
    }

    @Override
    public Set<String> listRoleCodes(String userId) {
        List<SysRole> sysRoles = sysRoleMapper.listSysRolesByUserId(userId);
        Set<String> roles = Sets.newHashSet();
        sysRoles.stream().filter(sysRole -> StringUtils.isNotBlank(sysRole))
                .forEach(sysRole -> roles.addAll(Arrays.asList(sysRole.getRoleCode().trim().split(","))));
        return roles;
    }

}
package com.yk.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.yk.common.exception.ParameterException;
import com.yk.common.text.Convert;
import com.yk.common.util.AppUtils;
import com.yk.common.util.StringUtils;
import com.yk.common.util.TimeUtils;
import com.yk.system.mapper.RoleMenuMapper;
import com.yk.system.mapper.SysRoleMapper;
import com.yk.system.mapper.UserRoleMapper;
import com.yk.system.model.pojo.RoleMenu;
import com.yk.system.model.pojo.SysRole;
import com.yk.system.model.pojo.UserRole;
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
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public int insertSysRole(SysRole sysRole) {
        sysRole.setId(AppUtils.randomId());
        sysRole.setStatus(1);
        sysRole.setCreateTime(TimeUtils.getCurrentDatetime());
        sysRole.setUpdateTime(TimeUtils.getCurrentDatetime());
        sysRoleMapper.insertSysRole(sysRole);

        //新增角色菜单信息
        int i = insertRoleMenuBatch(sysRole);

        return i;
    }

    private int insertRoleMenuBatch(SysRole sysRole) {
        List<RoleMenu> list = Lists.newArrayList();
        if (StringUtils.isBlank(sysRole.getMenuId())) {
            return 1;
        }
        String[] menuIds = sysRole.getMenuId().split(",");
        for (String menuId : menuIds) {
            RoleMenu rm = new RoleMenu();
            rm.setRoleId(sysRole.getId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        return roleMenuMapper.insertRoleMenuBatch(list);
    }

    @Override
    public int insertSysRoleBatch(List<SysRole> list) {
        return sysRoleMapper.insertSysRoleBatch(list);
    }

    @Override
    public int updateSysRole(SysRole sysRole) {
        sysRole.setUpdateTime(TimeUtils.getCurrentDatetime());
        sysRoleMapper.updateSysRole(sysRole);

        //先删除角色菜单关系, 再添加
        roleMenuMapper.deleteRoleMenuRealByRoleId(sysRole.getId());
        return insertRoleMenuBatch(sysRole);
    }

    @Override
    public int deleteSysRoleById(String id) {
        checkRoleInfoIsDel(id);
        return sysRoleMapper.deleteSysRoleById(id);
    }

    private void checkRoleInfoIsDel(String id) {
        checkRoleAllowed(id);
        //判断该角色是否已分配
        int count = countUserRoleByRoleId(id);
        if (count > 0) {
            throw new ParameterException(StringUtils.format("该角色已分配, 不能删除"));
        }
    }

    private int countUserRoleByRoleId(String id) {
        return userRoleMapper.countUserRoleByRoleId(id);
    }

    private void checkRoleAllowed(String id) {
        if (StringUtils.isNotBlank(id) && "1".equals(id)) {
            throw new ParameterException("不允许操作超级管理员角色");
        }
    }

    @Override
    public int deleteBatchSysRoleByIds(List<String> ids) {
        ids.forEach(id -> checkRoleInfoIsDel(id));
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

    @Override
    public String checkRoleNameUnique(SysRole role) {
        return checkRoleNameAndCodeUnique(role);
    }

    private String checkRoleNameAndCodeUnique(SysRole role) {
        String id = StringUtils.isBlank(role.getId()) ? "0" : role.getId();
        String roleId = sysRoleMapper.checkRoleNameAndCodeUnique(role);
        if (StringUtils.isNotBlank(roleId) && id.equals(roleId)) {
            return "0";
        }
        return "1";
    }

    @Override
    public String checkRoleCodeUnique(SysRole role) {
        return checkRoleNameAndCodeUnique(role);
    }

    @Override
    public int insertAuthUsers(String roleId, String userIds) {
        String[] userId = Convert.toStrArray(userIds);
        List<UserRole> list = Lists.newArrayList();
        for (String s : userId) {
            UserRole userRole = new UserRole();
            userRole.setUserId(s);
            userRole.setRoleId(roleId);
            list.add(userRole);
        }
        return userRoleMapper.insertUserRoleBatch(list);
    }

    @Override
    public int deleteAuthUsers(String roleId, String userIds) {
        return userRoleMapper.delUserRoleInfos(roleId, Convert.toStrArray(userIds));
    }

}
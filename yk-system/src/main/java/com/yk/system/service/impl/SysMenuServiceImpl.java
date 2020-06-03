package com.yk.system.service.impl;

import com.google.common.collect.Lists;
import com.yk.system.mapper.SysMenuMapper;
import com.yk.system.model.pojo.SysMenu;
import com.yk.system.model.pojo.SysUser;
import com.yk.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: YK-Platform
 * @description: 系统菜单实现类
 * @author: YuKai Fan
 * @create: 2020-06-03 16:12
 **/
@Service
@Transactional
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public int insertSysMenu(SysMenu sysUser) {
        return 0;
    }

    @Override
    public int insertSysMenuBatch(List<SysMenu> list) {
        return 0;
    }

    @Override
    public int updateSysMenu(SysMenu sysUser) {
        return 0;
    }

    @Override
    public int deleteSysMenuById(String id) {
        return 0;
    }

    @Override
    public int deleteBatchSysMenuByIds(List<String> ids) {
        return 0;
    }

    @Override
    public int deleteSysMenuRealById(String id) {
        return 0;
    }

    @Override
    public int deleteBatchSysMenuRealByIds(List<String> list) {
        return 0;
    }

    @Override
    public SysMenu getSysMenuById(String id) {
        return null;
    }

    @Override
    public List<SysMenu> listSysMenusByUser(SysUser sysUser) {
        List<SysMenu> menus = Lists.newArrayList();
        if (sysUser.isAdmin()) {
            menus = sysMenuMapper.listMenuAll();
        } else {

        }
        return getChildMenus(menus, "0");
    }

    private List<SysMenu> getChildMenus(List<SysMenu> list, String parentId) {
        List<SysMenu> reList = Lists.newArrayList();
        list.stream().filter(sysMenu -> sysMenu.getParentId().equals(parentId))
                .forEach(sysMenu -> {
                    rebuilding(list, sysMenu);
                    reList.add(sysMenu);
                });

        return reList;
    }

    private void rebuilding(List<SysMenu> list, SysMenu t) {
        List<SysMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        childList.stream().filter(child -> hasChild(list, child))
                .forEach(child -> childList.forEach(tChild -> {rebuilding(list, tChild);}));
    }

    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t) {
        List<SysMenu> cList = Lists.newArrayList();
        list.stream().filter(sysMenu -> sysMenu.getParentId().equals(t.getId()))
                .forEach(sysMenu -> cList.add(sysMenu));

        return cList;
    }

    /**
     * 判断是否有子节点
     * @param list
     * @param t
     * @return
     */
    private boolean hasChild(List<SysMenu> list, SysMenu t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }


}
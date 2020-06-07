package com.yk.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.yk.common.entity.Ztree;
import com.yk.common.util.AppUtils;
import com.yk.common.util.StringUtils;
import com.yk.common.util.TimeUtils;
import com.yk.system.mapper.SysMenuMapper;
import com.yk.system.model.pojo.SysMenu;
import com.yk.system.model.pojo.SysUser;
import com.yk.system.model.query.SysMenuQuery;
import com.yk.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
    public int insertSysMenu(SysMenu sysMenu) {
        sysMenu.setId(AppUtils.randomId());
        sysMenu.setStatus(1);
        sysMenu.setCreateTime(TimeUtils.getCurrentDatetime());
        sysMenu.setUpdateTime(TimeUtils.getCurrentDatetime());
        return sysMenuMapper.insertSysMenu(sysMenu);
    }

    @Override
    public int insertSysMenuBatch(List<SysMenu> list) {
        return 0;
    }

    @Override
    public int updateSysMenu(SysMenu sysMenu) {
        sysMenu.setUpdateTime(TimeUtils.getCurrentDatetime());
        return sysMenuMapper.updateSysMenu(sysMenu);
    }

    @Override
    public int deleteSysMenuById(String id) {
        return sysMenuMapper.deleteSysMenuById(id);
    }

    @Override
    public int deleteBatchSysMenuByIds(List<String> ids) {
        return sysMenuMapper.deleteBatchSysMenuByIds(ids);
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
        return sysMenuMapper.getSysMenuById(id);
    }

    @Override
    public List<SysMenu> listSysMenusByUser(SysUser sysUser) {
        List<SysMenu> menus;
        if (sysUser.isAdmin()) {
            menus = sysMenuMapper.listMenuNotButtonAll();
        } else {
            //根据用户id获取菜单集合(不包含按钮)
            menus = sysMenuMapper.listMenuNotButtonByUserId(sysUser.getId());
        }
        return getChildMenus(menus, "0");
    }

    @Override
    public List<SysMenu> listSysMenus(int start, int pageSize, SysMenuQuery sysMenuQuery) {
        PageHelper.startPage(start, pageSize);
        return this.listSysMenus(sysMenuQuery, null);
    }

    @Override
    public List<SysMenu> listSysMenus(SysMenuQuery sysMenuQuery, String userId) {
        List<SysMenu> menus;
        if (SysUser.isAdmin(userId)) {
            menus = sysMenuMapper.listSysMenus(sysMenuQuery);
        } else {
            //根据用户id获取菜单集合
            sysMenuQuery.setUserId(userId);
            menus = sysMenuMapper.listSysMenusByUserId(sysMenuQuery);
        }
        return menus;
    }

    @Override
    public List<Ztree> menuTreeData(String userId) {
        SysMenuQuery sysMenuQuery = new SysMenuQuery();
        List<SysMenu> sysMenus = this.listSysMenus(sysMenuQuery, userId);
        List<Ztree> ztrees = initZtree(sysMenus);
        return ztrees;
    }

    @Override
    public String checkMenuNameUnique(SysMenu menu) {
        String id = StringUtils.isBlank(menu.getId())?"0":menu.getId();
        SysMenu info = sysMenuMapper.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());
        if (StringUtils.isNotBlank(info) && !id.equals(info.getId())) {
            return "1";
        } else {
            return "0";
        }
    }

    private List<Ztree> initZtree(List<SysMenu> sysMenus) {
        return initZtree(sysMenus, null, false);
    }

    private List<Ztree> initZtree(List<SysMenu> sysMenus, List<String> roleMenuList, boolean permsFlag) {
        List<Ztree> ztrees = Lists.newArrayList();
        boolean isCheck = !CollectionUtils.isEmpty(roleMenuList);
        for (SysMenu menu : sysMenus) {
            Ztree ztree = new Ztree();
            ztree.setId(menu.getId());
            ztree.setpId(menu.getParentId());
            ztree.setName(transMenuName(menu, permsFlag));
            ztree.setTitle(menu.getMenuName());
            if (isCheck) {
                ztree.setChecked(roleMenuList.contains(menu.getId() + menu.getPerms()));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    private String transMenuName(SysMenu menu, boolean permsFlag) {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getMenuName());
        if (permsFlag) {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
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
                .forEach(child -> childList.forEach(tChild -> {
                    rebuilding(list, tChild);
                }));
    }

    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t) {
        List<SysMenu> cList = Lists.newArrayList();
        list.stream().filter(sysMenu -> sysMenu.getParentId().equals(t.getId()))
                .forEach(sysMenu -> cList.add(sysMenu));

        return cList;
    }

    /**
     * 判断是否有子节点
     *
     * @param list
     * @param t
     * @return
     */
    private boolean hasChild(List<SysMenu> list, SysMenu t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }


}
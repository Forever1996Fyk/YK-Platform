package com.yk.system.model.pojo;

import com.google.common.collect.Lists;
import com.yk.common.entity.BaseEntity;
import lombok.Data;

import java.util.List;


/**
 * 系统菜单表
 *
 * @author YuKai Fan
 * @create 2020-06-03 16:09:30
 */
@Data
public class SysMenu extends BaseEntity {
	private static final long serialVersionUID = -6264402254144162490L;

	//菜单唯一标识
    private String id;
    //菜单名称
    private String menuName;
    //父菜单id
    private String parentId;
    //菜单显示顺序
    private Integer menuSort;
    //请求地址
    private String url;
    //打开方式(menuItem页面标签, menuBlank新窗口)
    private String target;
    //菜单类型(1目录, 2菜单, 3按钮)
    private Integer menuType;
    //权限标识
    private String perms;
    //菜单图标
    private String icon;
    //菜单状态(0删除, 1显示, 2隐藏)
    private Integer status;
    //备注
    private String remark;
    //子菜单
    private List<SysMenu> children = Lists.newArrayList();

}

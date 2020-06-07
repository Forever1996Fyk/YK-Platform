package com.yk.system.model.query;

import com.yk.system.model.pojo.SysMenu;
import lombok.Data;

/**
 * 系统菜单查询对象 tb_sys_menu
 * 
 * @author YuKai Fan
 * @date 2020-06-07 11:26:54
 */
@Data
public class SysMenuQuery extends SysMenu {
    //用户id
    private String userId;
}

package com.yk.system.model.pojo;

import com.yk.common.entity.BaseEntity;
import lombok.Data;

/**
 * @program: YK-Platform
 * @description: 角色菜单关联
 * @author: YuKai Fan
 * @create: 2020-06-08 20:11
 **/
@Data
public class RoleMenu extends BaseEntity {
    /**
     * 角色id
     */
    private String roleId;

    /**
     * 菜单id
     */
    private String menuId;
}
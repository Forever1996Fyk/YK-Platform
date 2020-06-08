package com.yk.system.model.query;


import com.yk.system.model.pojo.SysUser;
import lombok.Data;

/**
 * @program: YK-Platform
 * @description: 系统用户查询实体
 * @author: YuKai Fan
 * @create: 2020-06-02 21:29
 **/
@Data
public class SysUserQuery extends SysUser {
    /**
     * 角色id
     */
    private String roleId;
}
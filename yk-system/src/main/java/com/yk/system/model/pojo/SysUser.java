package com.yk.system.model.pojo;

import com.yk.common.entity.BaseEntity;
import com.yk.common.util.StringUtils;
import lombok.Data;

import java.io.Serializable;


/**
 * 系统用户表
 *
 * @author YuKai Fan
 * @create 2020-06-02 20:29:24
 */
@Data
public class SysUser extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //用户唯一标识
    private String id;
    //部门ID
    private String deptId;
    //登录账号
    private String account;
    //用户名
    private String userName;
    //登录密码
    private String password;
    //手机号
    private String phone;
    //用户邮箱
    private String email;
    //用户性别(1 男, 2女)
    private Integer sex;
    //用户头像路径
    private String avatar;
    //用户类型
    private Integer userType;
    //盐加密
    private String salt;
    //账号状态(0已删除, 1正常, 2停用)
    private Integer status;

    /**
     * 判断是否是超级管理员
     * @return
     */
    public boolean isAdmin() {
        return isAdmin(this.id);
    }

    private boolean isAdmin(String userId) {
        return StringUtils.isNotBlank(userId) && "1".equals(userId);
    }
}

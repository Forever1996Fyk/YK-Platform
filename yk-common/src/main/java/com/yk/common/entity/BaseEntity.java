package com.yk.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: YK-Platform
 * @description: 基础实体类
 * @author: YuKai Fan
 * @create: 2020-06-02 20:36
 **/
@Data
public class BaseEntity implements Serializable {
    //创建人
    private String createUserId;
    //创建时间
    private String createTime;
    //更新人
    private String updateUserId;
    //更新时间
    private String updateTime;
    //备注
    private String remark;
}
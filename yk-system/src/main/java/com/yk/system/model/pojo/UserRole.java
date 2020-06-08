package com.yk.system.model.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import lombok.Data;
import com.yk.common.entity.BaseEntity;

/**
 * 用户角色关联对象 tb_user_role
 * 
 * @author YuKai Fan
 * @create 2020-06-08 20:30:07
 */
@Data
public class UserRole extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 用户id */
    private String userId;
    /** 角色id */
    private String roleId;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("roleId", getRoleId())
            .toString();
    }
}

package com.yk.system.model.pojo;

import com.yk.common.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import lombok.Data;

/**
 * 系统角色对象 tb_sys_role
 * 
 * @author YuKai Fan
 * @create 2020-06-05 22:28:42
 */
@Data
public class SysRole extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 系统角色表 */
    private String id;
    /** 角色名称 */
    private String roleName;
    /** 角色权限code */
    private String roleCode;
    /** 数据范围(1全部数据, 2自定义数据权限, 3本部门数据权限, 4本部门及以下数据权限) */
    private Integer dataScope;
    /** 显示顺序 */
    private Integer roleSort;
    /** 状态(0已删除,1正常) */
    private Integer status;
    /** 备注 */
    private String remark;
    /** 创建人id */
    private String createUserId;
    /** 创建时间 */
    private String createTime;
    /** 更新人id */
    private String updateUserId;
    /** 更新时间 */
    private String updateTime;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("roleName", getRoleName())
            .append("roleCode", getRoleCode())
            .append("dataScope", getDataScope())
            .append("roleSort", getRoleSort())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createUserId", getCreateUserId())
            .append("createTime", getCreateTime())
            .append("updateUserId", getUpdateUserId())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

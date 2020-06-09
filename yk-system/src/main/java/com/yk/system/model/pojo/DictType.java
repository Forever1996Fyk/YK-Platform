package com.yk.system.model.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import lombok.Data;
import com.yk.common.entity.BaseEntity;

/**
 * 字典类型对象 tb_dict_type
 * 
 * @author YuKai Fan
 * @create 2020-06-09 11:14:10
 */
@Data
public class DictType extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 字典主键 */
    private String id;
    /** 字典名称 */
    private String dictName;
    /** 字典类型 */
    private String dictType;
    /** 状态（1正常 2停用 0删除） */
    private Integer status;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("dictName", getDictName())
            .append("dictType", getDictType())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createUserId", getCreateUserId())
            .append("createTime", getCreateTime())
            .append("updateUserId", getUpdateUserId())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

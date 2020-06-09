package com.yk.system.model.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import lombok.Data;
import com.yk.common.entity.BaseEntity;

/**
 * 字典数据对象 tb_dict_data
 * 
 * @author YuKai Fan
 * @create 2020-06-09 11:14:10
 */
@Data
public class DictData extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 字典编码 */
    private String id;
    /** 字典排序 */
    private Integer dictSort;
    /** 字典标签 */
    private String dictLabel;
    /** 字典键值 */
    private String dictValue;
    /** 字典类型 */
    private String dictType;
    /** 样式属性 */
    private String cssClass;
    /** 表格回显样式 */
    private String listClass;
    /** 是否默认 */
    private Integer isDefault;
    /** 状态 */
    private Integer status;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("dictSort", getDictSort())
            .append("dictLabel", getDictLabel())
            .append("dictValue", getDictValue())
            .append("dictType", getDictType())
            .append("cssClass", getCssClass())
            .append("listClass", getListClass())
            .append("isDefault", getIsDefault())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createUserId", getCreateUserId())
            .append("createTime", getCreateTime())
            .append("updateUserId", getUpdateUserId())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

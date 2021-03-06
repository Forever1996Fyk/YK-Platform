package com.yk.generator.model.pojo;


import com.yk.common.entity.BaseEntity;
import com.yk.common.util.StringUtils;
import lombok.Data;


/**
 * 代码生成业务表字段
 *
 * @author YuKai Fan
 * @create 2020-06-03 17:19:44
 */
@Data
public class GenTableColumn extends BaseEntity {
    private static final long serialVersionUID = 6971765873120245211L;
    /**
     * 列名标识
     */
    private String id;
    /**
     * 归属表编号
     */
    private String tableId;
    /**
     * 列名称
     */
    private String columnName;
    /**
     * 列描述
     */
    private String columnComment;
    /**
     * 列类型
     */
    private String columnType;
    /**
     * JAVA类型
     */
    private String javaType;
    /**
     * JAVA字段名
     */
    private String javaField;
    /**
     * 是否主键（1是）
     */
    private String pk;
    /**
     * 是否自增（1是）
     */
    private String increment;
    /**
     * 是否必填（1是）
     */
    private String required;
    /**
     * 是否为插入字段（1是）
     */
    private String isInsert;
    /**
     * 是否编辑字段（1是）
     */
    private String edit;
    /**
     * 是否列表字段（1是）
     */
    private String list;
    /**
     * 是否查询字段（1是）
     */
    private String query;
    /**
     * 查询方式（等于、不等于、大于、小于、范围）
     */
    private String queryType;
    /**
     * 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）
     */
    private String htmlType;
    /**
     * 字典类型
     */
    private String dictType;
    /**
     * 排序
     */
    private Integer sort;

    /**
     * 判断是否是主键
     * @return
     */
    public boolean checkPk() {
        return checkPk(this.pk);
    }

    private boolean checkPk(String pk) {
        return StringUtils.equals("1", pk);
    }

    /**
     * 判断是否是通用列
     * @return
     */
    public boolean commonColumn() {
        return commonColumn(this.javaField);
    }

    public boolean commonColumn(String javaField) {
        return StringUtils.equalsAnyIgnoreCase(javaField,
                "createUserId", "createTime", "updateUserId", "updateTime", "remark",
                //TreeEntity
                "parentName", "parentId", "orderNum", "ancestors");
    }

    /**
     * 判断是否是集合
     * @return
     */
    public boolean checkList() {
        return checkList(this.list);
    }

    public boolean checkList(String list)
    {
        return list != null && StringUtils.equals("1", list);
    }
}

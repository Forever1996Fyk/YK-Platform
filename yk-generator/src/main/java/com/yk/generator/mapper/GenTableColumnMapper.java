package com.yk.generator.mapper;

import com.yk.generator.model.pojo.GenTableColumn;

import java.util.List;

/**
 * @ClassName GenTableColumnMapper
 * @Description 生成表列
 * @Author YuKai Fan
 * @Date 2020/6/4 21:07
 * @Version 1.0
 **/
public interface GenTableColumnMapper {
    /**
     * 根据表名称查询列信息
     * @author YuKai Fan
     * @param tableName
     * @return java.util.List<com.yk.generator.model.pojo.GenTableColumn>
     * @date 2020/6/4 21:09
     */
    List<GenTableColumn> listDbTableColumnsByName(String tableName);

    /**
     * 新增数据库表列字段
     * @author YuKai Fan
     * @param genTableColumn
     * @return void
     * @date 2020/6/4 21:32
     */
    int insertGenTableColumn(GenTableColumn genTableColumn);

    /**
     * 根据tableId获取数据库表列集合
     * @param tableId
     * @return
     */
    List<GenTableColumn> listGenTableColumnsByTableId(String tableId);

    /**
     * 更新数据库表列信息
     * @param cenTableColumn
     */
    void updateGenTableColumn(GenTableColumn cenTableColumn);
}

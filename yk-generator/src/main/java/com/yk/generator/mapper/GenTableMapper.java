package com.yk.generator.mapper;

import com.yk.generator.model.pojo.GenTable;
import com.yk.generator.model.query.GenTableQuery;

import java.util.List;

/**
 * @program: YK-Platform
 * @description: 业务数据库表mapper
 * @author: YuKai Fan
 * @create: 2020-06-03 20:07
 **/
public interface GenTableMapper {

    /**
     * 查询业务数据库表
     * @param genTableQuery
     * @return
     */
    List<GenTable> listGenTables(GenTableQuery genTableQuery);

    /**
     * 获取数据库列表
     * @param genTableQuery
     * @return
     */
    List<GenTable> listDbTables(GenTableQuery genTableQuery);

    /**
     * 根据表名称获取数据库表集合
     * @author YuKai Fan
     * @param tableNames
     * @return java.util.List<com.yk.generator.model.pojo.GenTable>
     * @date 2020/6/4 20:33
     */
    List<GenTable> listDbTablesByNames(String[] tableNames);

    /**
     * 新增表信息
     * @author YuKai Fan
     * @param table
     * @return int
     * @date 2020/6/4 20:59
     */
    int insertGenTable(GenTable table);

    /**
     * 根据tableName获取数据库表信息
     * @param tableName
     * @return
     */
    GenTable getGenTableByName(String tableName);

    /**
     * 根据表id获取数据表
     * @param tableId
     * @return
     */
    GenTable getGenTableById(String tableId);
}
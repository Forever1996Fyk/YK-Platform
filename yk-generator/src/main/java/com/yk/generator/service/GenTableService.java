package com.yk.generator.service;

import com.yk.generator.model.pojo.GenTable;
import com.yk.generator.model.query.GenTableQuery;

import java.util.List;

/**
 * @program: YK-Platform
 * @description: 代码生成列表
 * @author: YuKai Fan
 * @create: 2020-06-03 19:58
 **/
public interface GenTableService {

    /**
     * 查询业务数据库表
     * @param genTableQuery
     * @return
     */
    List<GenTable> listGenTables(GenTableQuery genTableQuery);

    /**
     * 查询业务数据库表(分页)
     *
     * @param start
     * @param pageSize
     * @param genTableQuery
     * @return
     */
    List<GenTable> listGenTables(int start, int pageSize, GenTableQuery genTableQuery);

    /**
     * 获取数据库列表
     * @param genTableQuery
     * @return
     */
    List<GenTable> listDbTables(GenTableQuery genTableQuery);

    /**
     * 获取数据库列表(分页)
     * @param start
     * @param pageSize
     * @param genTableQuery
     * @return
     */
    List<GenTable> listDbTables(int start, int pageSize, GenTableQuery genTableQuery);

    /**
     * 根据表名称获取数据库表集合
     * @author YuKai Fan 
     * @param tableNames
     * @return java.util.List<com.yk.generator.model.pojo.GenTable>
     * @date 2020/6/4 20:32
     */
    List<GenTable> listDbTablesByNames(String[] tableNames);

    /**
     * 导入表结构
     * @author YuKai Fan
     * @param tablesList
     * @return void
     * @date 2020/6/4 20:42
     */
    void importGenTable(List<GenTable> tablesList);

    /**
     * 生成代码
     * @param tableName
     * @return
     */
    byte[] generatorCode(String tableName);

    /**
     * 批量生成代码
     * @param tableNameArr
     * @return
     */
    byte[] generatorCode(String[] tableNameArr);

    /**
     * 根据表id获取数据表
     * @param tableId
     * @return
     */
    GenTable getGenTableById(String tableId);
}
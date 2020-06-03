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
}
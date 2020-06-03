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
}
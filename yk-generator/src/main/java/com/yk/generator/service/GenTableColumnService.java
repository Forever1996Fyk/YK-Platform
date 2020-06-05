package com.yk.generator.service;

import com.yk.generator.model.pojo.GenTableColumn;
import com.yk.generator.model.query.GenTableColumnQuery;

import java.util.List;

/**
 * @program: YK-Platform
 * @description:
 * @author: YuKai Fan
 * @create: 2020-06-05 23:26
 **/
public interface GenTableColumnService {
    /**
     * 根据tableId获取列集合
     * @param genTableColumnQuery
     * @return
     */
    List<GenTableColumn> listGenTableColumnsByTableId(GenTableColumnQuery genTableColumnQuery);

    /**
     * 根据tableId获取列集合(分页)
     * @param start
     * @param pageSize
     * @param genTableColumnQuery
     * @return
     */
    List<GenTableColumn> listGenTableColumnsByTableId(int start, int pageSize, GenTableColumnQuery genTableColumnQuery);
}
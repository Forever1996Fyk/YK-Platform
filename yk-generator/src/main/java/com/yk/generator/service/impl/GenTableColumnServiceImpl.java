package com.yk.generator.service.impl;

import com.github.pagehelper.PageHelper;
import com.yk.generator.mapper.GenTableColumnMapper;
import com.yk.generator.model.pojo.GenTableColumn;
import com.yk.generator.model.query.GenTableColumnQuery;
import com.yk.generator.service.GenTableColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: YK-Platform
 * @description:
 * @author: YuKai Fan
 * @create: 2020-06-05 23:50
 **/
@Service
@Transactional
public class GenTableColumnServiceImpl implements GenTableColumnService {
    @Autowired
    private GenTableColumnMapper genTableColumnMapper;

    @Override
    public List<GenTableColumn> listGenTableColumnsByTableId(GenTableColumnQuery genTableColumnQuery) {
        return genTableColumnMapper.listGenTableColumnsByTableId(genTableColumnQuery.getTableId());
    }

    @Override
    public List<GenTableColumn> listGenTableColumnsByTableId(int start, int pageSize, GenTableColumnQuery genTableColumnQuery) {
        PageHelper.startPage(start, pageSize);
        return this.listGenTableColumnsByTableId(genTableColumnQuery);
    }
}
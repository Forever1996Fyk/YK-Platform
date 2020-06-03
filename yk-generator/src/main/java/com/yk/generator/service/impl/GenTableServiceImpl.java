package com.yk.generator.service.impl;

import com.github.pagehelper.PageHelper;
import com.yk.generator.mapper.GenTableMapper;
import com.yk.generator.model.pojo.GenTable;
import com.yk.generator.model.query.GenTableQuery;
import com.yk.generator.service.GenTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: YK-Platform
 * @description: 代码生成列表
 * @author: YuKai Fan
 * @create: 2020-06-03 19:58
 **/
@Service
@Transactional
public class GenTableServiceImpl implements GenTableService {
    @Autowired
    private GenTableMapper genTableMapper;

    @Override
    public List<GenTable> listGenTables(GenTableQuery genTableQuery) {
        return genTableMapper.listGenTables(genTableQuery);
    }

    @Override
    public List<GenTable> listGenTables(int start, int pageSize, GenTableQuery genTableQuery) {
        PageHelper.offsetPage(start, pageSize);
        return this.listGenTables(genTableQuery);
    }

    @Override
    public List<GenTable> listDbTables(GenTableQuery genTableQuery) {
        return genTableMapper.listDbTables(genTableQuery);
    }

    @Override
    public List<GenTable> listDbTables(int start, int pageSize, GenTableQuery genTableQuery) {
        PageHelper.offsetPage(start, pageSize);
        return this.listDbTables(genTableQuery);
    }
}
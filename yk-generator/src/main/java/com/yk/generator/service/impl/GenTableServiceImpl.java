package com.yk.generator.service.impl;

import com.github.pagehelper.PageHelper;
import com.yk.common.util.AppUtils;
import com.yk.generator.mapper.GenTableColumnMapper;
import com.yk.generator.mapper.GenTableMapper;
import com.yk.generator.model.pojo.GenTable;
import com.yk.generator.model.pojo.GenTableColumn;
import com.yk.generator.model.query.GenTableQuery;
import com.yk.generator.service.GenTableService;
import com.yk.generator.util.GenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Transactional(rollbackFor = Exception.class)
public class GenTableServiceImpl implements GenTableService {
    private static final Logger logger = LoggerFactory.getLogger(GenTableServiceImpl.class);
    @Autowired
    private GenTableMapper genTableMapper;
    @Autowired
    private GenTableColumnMapper genTableColumnMapper;

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

    @Override
    public List<GenTable> listDbTablesByNames(String[] tableNames) {
        return genTableMapper.listDbTablesByNames(tableNames);
    }

    @Override
    public void importGenTable(List<GenTable> tablesList) {
        tablesList.forEach(table -> {
            try {
                table.setId(AppUtils.randomId());
                String tableName = table.getTableName();
                GenUtils.initTable(table);
                int row = genTableMapper.insertGenTable(table);
                if (row > 0) {
                    //保存列信息
                    List<GenTableColumn> genTableColumns = genTableColumnMapper.listDbTableColumnsByName(tableName);
                    genTableColumns.forEach(genTableColumn -> {
                        GenUtils.initColumnField(genTableColumn, table);
                        genTableColumn.setId(AppUtils.randomId());
                        genTableColumnMapper.insertGenTableColumn(genTableColumn);
                    });
                }
            } catch (Exception e) {
                logger.error("表名[{}] 导入失败: [{}]", table.getTableName(), e.getMessage());
            }
        });
    }
}
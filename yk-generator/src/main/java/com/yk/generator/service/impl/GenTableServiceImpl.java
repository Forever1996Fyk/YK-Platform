package com.yk.generator.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.yk.common.constant.ComConstants;
import com.yk.common.constant.GenConstants;
import com.yk.common.util.AppUtils;
import com.yk.common.util.StringUtils;
import com.yk.common.util.TimeUtils;
import com.yk.generator.mapper.GenTableColumnMapper;
import com.yk.generator.mapper.GenTableMapper;
import com.yk.generator.model.pojo.GenTable;
import com.yk.generator.model.pojo.GenTableColumn;
import com.yk.generator.model.query.GenTableQuery;
import com.yk.generator.service.GenTableService;
import com.yk.generator.util.GenUtils;
import com.yk.generator.util.VelocityInitializer;
import com.yk.generator.util.VelocityUtils;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

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
        PageHelper.startPage(start, pageSize);
        return this.listGenTables(genTableQuery);
    }

    @Override
    public List<GenTable> listDbTables(GenTableQuery genTableQuery) {
        return genTableMapper.listDbTables(genTableQuery);
    }

    @Override
    public List<GenTable> listDbTables(int start, int pageSize, GenTableQuery genTableQuery) {
        PageHelper.startPage(start, pageSize);
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
                table.setCreateTime(TimeUtils.getCurrentDatetime());
                table.setUpdateTime(TimeUtils.getCurrentDatetime());
                table.setTplCategory(GenConstants.TPL_CRUD);
                int row = genTableMapper.insertGenTable(table);
                if (row > 0) {
                    //保存列信息
                    List<GenTableColumn> genTableColumns = genTableColumnMapper.listDbTableColumnsByName(tableName);
                    genTableColumns.forEach(genTableColumn -> {
                        GenUtils.initColumnField(genTableColumn, table);
                        genTableColumn.setId(AppUtils.randomId());
                        genTableColumn.setCreateTime(TimeUtils.getCurrentDatetime());
                        genTableColumn.setUpdateTime(TimeUtils.getCurrentDatetime());
                        genTableColumnMapper.insertGenTableColumn(genTableColumn);
                    });
                }
            } catch (Exception e) {
                logger.error("表名[{}] 导入失败: [{}]", table.getTableName(), e.getMessage());
            }
        });
    }

    @Override
    public byte[] generatorCode(String tableName) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        GenTable table = genTableMapper.getGenTableByName(tableName);
        List<GenTableColumn> columns = genTableColumnMapper.listGenTableColumnsByTableId(table.getId());
        table.setColumns(columns);
        GenUtils.generatorCode(table, zip);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    @Override
    public byte[] generatorCode(String[] tableNameArr) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNameArr) {
            GenTable table = genTableMapper.getGenTableByName(tableName);
            List<GenTableColumn> columns = genTableColumnMapper.listGenTableColumnsByTableId(table.getId());
            table.setColumns(columns);
            GenUtils.generatorCode(table, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    @Override
    public GenTable getGenTableById(String tableId) {
        GenTable genTable = genTableMapper.getGenTableById(tableId);
        List<GenTableColumn> genTableColumns = genTableColumnMapper.listGenTableColumnsByTableId(tableId);
        genTable.setColumns(genTableColumns);
        setTableFromOptions(genTable);
        return genTable;
    }

    @Override
    public void validateEdit(GenTable genTable) {
        if (GenConstants.TPL_TREE.equals(genTable.getTplCategory())) {
//            if (StringUtils.isBlank(genTable.getTreeCode())) {
//                throw new BusinessException("树编码字段不能为空");
//            } else if (StringUtils.isBlank(genTable.getTreeParentCode())) {
//                throw new BusinessException("树父编码字段不能为空");
//            } else if (StringUtils.isBlank(genTable.getTreeName())) {
//                throw new BusinessException("树名称字段不能为空");
//            }
        }
    }

    @Override
    public void updateGenTable(GenTable genTable) {
        genTable.setUpdateTime(TimeUtils.getCurrentDatetime());
        int row = genTableMapper.updateGenTable(genTable);
        if (row > 0) {
            for (GenTableColumn genTableColumn : genTable.getColumns()) {
                genTableColumn.setUpdateTime(TimeUtils.getCurrentDatetime());
                genTableColumnMapper.updateGenTableColumn(genTableColumn);
            }
        }
    }

    @Override
    public Map<String, Object> previewCode(String tableId) {
        Map<String, Object> map = Maps.newLinkedHashMap();
        GenTable genTable = genTableMapper.getGenTableById(tableId);
        //获取列信息
        List<GenTableColumn> genTableColumns = genTableColumnMapper.listGenTableColumnsByTableId(tableId);
        genTable.setColumns(genTableColumns);
        GenUtils.setPkColumn(genTable, genTableColumns);
        VelocityInitializer.init();

        VelocityContext content = VelocityUtils.prepareContext(genTable);

        //获取模板列表
        List<String> templateList = VelocityUtils.getTemplateList(genTable.getTplCategory());
        templateList.forEach(template -> {
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, ComConstants.UTF8);
            tpl.merge(content, sw);
            map.put(template, sw.toString());
        });
        return map;
    }

    /**
     * 设置代码生成其他选项值
     *
     * @param genTable 设置后的生成对象
     */
    public void setTableFromOptions(GenTable genTable) {
        JSONObject paramsObj = JSONObject.parseObject(genTable.getOptions());
        if (StringUtils.isNotNull(paramsObj)) {
            String treeCode = paramsObj.getString(GenConstants.TREE_CODE);
            String treeParentCode = paramsObj.getString(GenConstants.TREE_PARENT_CODE);
            String treeName = paramsObj.getString(GenConstants.TREE_NAME);
            genTable.setTreeCode(treeCode);
            genTable.setTreeParentCode(treeParentCode);
            genTable.setTreeName(treeName);
        }
    }
}
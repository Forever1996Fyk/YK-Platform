package com.yk.generator.controller;

import com.yk.common.dto.DataTablesViewPage;
import com.yk.common.dto.Result;
import com.yk.common.text.Convert;
import com.yk.generator.model.pojo.GenTable;
import com.yk.generator.model.pojo.GenTableColumn;
import com.yk.generator.model.query.GenTableColumnQuery;
import com.yk.generator.model.query.GenTableQuery;
import com.yk.generator.service.GenCodeService;
import com.yk.generator.service.GenTableColumnService;
import com.yk.generator.service.GenTableService;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @program: YK-Platform
 * @description: 代码生成controller
 * @author: YuKai Fan
 * @create: 2020-06-03 15:33
 **/
@RestController
@RequestMapping("/api/gen")
public class GenController {
    @Autowired
    private GenTableService genTableService;
    @Autowired
    private GenTableColumnService genTableColumnService;

    /**
     * 获取代码生成数据库表集合
     *
     * @param start
     * @param pageSize
     * @param genTableQuery
     * @return
     */
    @GetMapping("/listGenTables")
    @RequiresPermissions("tool:gen:list")
    public Result listGenTables(@RequestParam(value = "start", defaultValue = "0") int start,
                                @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                GenTableQuery genTableQuery) {
        List<GenTable> genTables = genTableService.listGenTables(start, pageSize, genTableQuery);
        return Result.success(new DataTablesViewPage<>(genTables));
    }

    /**
     * 获取数据库列表
     *
     * @param genTableQuery
     * @return
     */
    @GetMapping("/db/list")
    @RequiresPermissions("tool:gen:list")
    public Result dataList(@RequestParam(value = "start", defaultValue = "0") int start,
                           @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                           GenTableQuery genTableQuery) {
        List<GenTable> genTables = genTableService.listDbTables(start, pageSize, genTableQuery);
        return Result.success(new DataTablesViewPage<>(genTables));
    }

    /**
     * 查询数据表字段列表
     */
    @GetMapping("/listGenTableColumns")
    @RequiresPermissions("tool:gen:list")
    public Result listGenTableColumns(@RequestParam(value = "start", defaultValue = "0") int start,
                                      @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                      GenTableColumnQuery genTableColumnQuery) {
        List<GenTableColumn> list = genTableColumnService.listGenTableColumnsByTableId(start, pageSize, genTableColumnQuery);
        return Result.success(new DataTablesViewPage<>(list));
    }

    /**
     * 导入数据表
     *
     * @param tables
     * @return com.yk.common.dto.Result
     * @author YuKai Fan
     * @date 2020/6/4 21:47
     */
    @RequiresPermissions("tool:gen:list")
    @PostMapping("/importTable/{tables}")
    public Result importTable(@PathVariable("tables") String tables) {
        String[] tableNames = Convert.toStrArray(tables);
        List<GenTable> tablesList = genTableService.listDbTablesByNames(tableNames);
        genTableService.importGenTable(tablesList);
        return Result.success();
    }

    /**
     * 修改保存数据库表信息
     * @param genTable
     * @return
     */
    @PutMapping("/editGenTable")
    @RequiresPermissions("tool:gen:edit")
    public Result editGenTable(GenTable genTable) {
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return Result.success();
    }

    /**
     * 预览代码
     * @param tableId
     * @return
     */
    @GetMapping("/preview/{tableId}")
    @RequiresPermissions("tool:gen:preview")
    public Result preview(@PathVariable("tableId") String tableId) {
        Map<String, Object> map = genTableService.previewCode(tableId);
        return Result.success(map);
    }

    /**
     * 生成代码
     *
     * @param tableName
     * @param response
     */
    @GetMapping("/genCode/{tableName}")
    @RequiresPermissions("tool:gen:code")
    public void genCode(@PathVariable("tableName") String tableName, HttpServletResponse response) throws IOException {
        byte[] data = genTableService.generatorCode(tableName);
        genCode(response, data);
    }

    /**
     * 批量生成代码
     *
     * @param tableNames
     * @param response
     */
    @GetMapping("/batchGenCode/{tableNames}")
    @RequiresPermissions("tool:gen:code")
    public void batchGenCode(@PathVariable("tableNames") String tableNames, HttpServletResponse response) throws IOException {
        String[] tableNameArr = Convert.toStrArray(tableNames);
        byte[] data = genTableService.generatorCode(tableNameArr);
        genCode(response, data);

    }

    /**
     * 下载代码
     *
     * @param response
     * @param data
     * @throws IOException
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"yk-platform.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
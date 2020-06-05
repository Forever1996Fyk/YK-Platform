package com.yk.generator.controller;

import com.yk.common.dto.DataTablesViewPage;
import com.yk.common.dto.Result;
import com.yk.common.text.Convert;
import com.yk.generator.model.pojo.GenTable;
import com.yk.generator.model.query.GenTableQuery;
import com.yk.generator.service.GenCodeService;
import com.yk.generator.service.GenTableService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
    private GenCodeService genCodeService;
    @Autowired
    private GenTableService genTableService;

    /**
     * 获取代码生成数据库表集合
     * @param start
     * @param pageSize
     * @param genTableQuery
     * @return
     */
    @GetMapping("/listGenTables")
    public Result listGenTables(@RequestParam(value = "start", defaultValue = "0") int start,
                                @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                GenTableQuery genTableQuery) {
        List<GenTable> genTables = genTableService.listGenTables(start, pageSize, genTableQuery);
        return Result.success(new DataTablesViewPage<>(genTables));
    }

    /**
     * 获取数据库列表
     * @param genTableQuery
     * @return
     */
    @GetMapping("/db/list")
    public Result dataList(@RequestParam(value = "start", defaultValue = "0") int start,
                           @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                           GenTableQuery genTableQuery) {
        List<GenTable> genTables = genTableService.listDbTables(start, pageSize, genTableQuery);
        return Result.success(new DataTablesViewPage<>(genTables));
    }

    /**
     * 导入数据表
     * @author YuKai Fan
     * @param tables
     * @return com.yk.common.dto.Result
     * @date 2020/6/4 21:47
     */
    @PostMapping("/importTable/{tables}")
    public Result importTable(@PathVariable("tables") String tables) {
        String[] tableNames = Convert.toStrArray(tables);
        List<GenTable> tablesList = genTableService.listDbTablesByNames(tableNames);
        genTableService.importGenTable(tablesList);
        return Result.success();
    }

    /**
     * 生成代码
     * @param tableName
     * @param response
     */
    @PostMapping("/genCode/{tableName}")
    public void genCode(@PathVariable("tableName") String tableName, HttpServletResponse response) throws IOException {
        byte[] data = genTableService.generatorCode(tableName);
        genCode(response, data);

    }

    /**
     * 批量生成代码
     * @param tableNames
     * @param response
     */
    @PostMapping("/batchGenCode/{tableNames}")
    public void batchGenCode(@PathVariable("tableNames") String tableNames, HttpServletResponse response) throws IOException {
        String[] tableNameArr = Convert.toStrArray(tableNames);
        byte[] data = genTableService.generatorCode(tableNameArr);
        genCode(response, data);

    }

    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"yk-platform.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
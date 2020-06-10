package com.yk.web.controller.system;

import com.yk.common.dto.Result;
import com.yk.system.model.pojo.DictData;
import com.yk.system.model.query.DictDataQuery;
import com.yk.system.service.DictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.List;
import com.yk.common.dto.DataTablesViewPage;

/**
 * @program: YK-Platform
 * @description: 字典数据controller
 * @author: YuKai Fan
 * @create: 2020-06-09 11:14:10
 **/
@RestController
@RequestMapping("/api/system/dict/data")
public class DictDataController {
    @Autowired
    private DictDataService dictDataService;

    /**
     * 获取字典数据集合
     * @param start
     * @param pageSize
     * @param dictDataQuery
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("system:dict:list")
    public Result listDictDatas(@RequestParam(value = "start", defaultValue = "0") int start,
                               @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                               DictDataQuery dictDataQuery) {
        List<DictData> list = dictDataService.listDictDatas(start, pageSize, dictDataQuery);
        return Result.success("获取成功", new DataTablesViewPage<>(list));
    }

    /**
     * 新增字典数据
     * @param dictData
     * @return
     */
    @PostMapping("/addDictData")
    @RequiresPermissions("system:dict:add")
    public Result addDictData(@RequestBody DictData dictData) {
        return Result.response(dictDataService.insertDictData(dictData));
    }

    /**
     * 修改字典数据
     * @param dictData
     * @return
     */
    @PutMapping("/editDictData")
    @RequiresPermissions("system:dict:edit")
    public Result editDictData(@RequestBody DictData dictData) {
        return Result.response(dictDataService.updateDictData(dictData));
    }
    /**
     * 根据id删除字典数据
     * @param id
     * @return
     */
    @DeleteMapping("/deleteDictDataById/{id}")
    @RequiresPermissions("system:dict:delete")
    public Result deleteDictDataById(@PathVariable("id") String id) {
        return Result.response(dictDataService.deleteDictDataById(id));
    }

    /**
     * 批量删除字典数据
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteBatchDictDataByIds/{ids}")
    @RequiresPermissions("system:dict:delete")
    public Result deleteBatchDictDataByIds(@PathVariable("ids") List<String> ids) {
        return Result.response(dictDataService.deleteBatchDictDataByIds(ids));
    }

}
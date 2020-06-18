package com.yk.web.controller.system;

import com.yk.common.annotation.ActionLog;
import com.yk.common.dto.Result;
import com.yk.common.entity.Ztree;
import com.yk.common.enums.LogTypeEnum;
import com.yk.system.model.pojo.DictType;
import com.yk.system.model.query.DictTypeQuery;
import com.yk.system.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.List;

import com.yk.common.dto.DataTablesViewPage;

/**
 * @program: YK-Platform
 * @description: 字典类型controller
 * @author: YuKai Fan
 * @create: 2020-06-09 11:14:10
 **/
@RestController
@RequestMapping("/api/system/dict/type")
public class DictTypeController {
    @Autowired
    private DictTypeService dictTypeService;

    /**
     * 获取字典类型集合
     *
     * @param start
     * @param pageSize
     * @param dictTypeQuery
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("system:dict:list")
    public Result listDictTypes(@RequestParam(value = "start", defaultValue = "0") int start,
                                @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                DictTypeQuery dictTypeQuery) {
        List<DictType> list = dictTypeService.listDictTypes(start, pageSize, dictTypeQuery);
        return Result.success("获取成功", new DataTablesViewPage<>(list));
    }

    /**
     * 新增字典类型
     *
     * @param dictType
     * @return
     */
    @ActionLog(name = "字典类型", logType = LogTypeEnum.INSERT)
    @PostMapping("/addDictType")
    @RequiresPermissions("system:dict:add")
    public Result addDictType(@RequestBody DictType dictType) {
        return Result.response(dictTypeService.insertDictType(dictType));
    }

    /**
     * 修改字典类型
     *
     * @param dictType
     * @return
     */
    @ActionLog(name = "字典类型", logType = LogTypeEnum.UPDATE)
    @PutMapping("/editDictType")
    @RequiresPermissions("system:dict:edit")
    public Result editDictType(@RequestBody DictType dictType) {
        return Result.response(dictTypeService.updateDictType(dictType));
    }

    /**
     * 根据id删除字典类型
     *
     * @param id
     * @return
     */
    @ActionLog(name = "字典类型", logType = LogTypeEnum.DELETE)
    @DeleteMapping("/deleteDictTypeById/{id}")
    @RequiresPermissions("system:dict:delete")
    public Result deleteDictTypeById(@PathVariable("id") String id) {
        return Result.response(dictTypeService.deleteDictTypeById(id));
    }

    /**
     * 批量删除字典类型
     *
     * @param ids
     * @return
     */
    @ActionLog(name = "字典类型", logType = LogTypeEnum.DELETE)
    @DeleteMapping("/deleteBatchDictTypeByIds/{ids}")
    @RequiresPermissions("system:dict:delete")
    public Result deleteBatchDictTypeByIds(@PathVariable("ids") List<String> ids) {
        return Result.response(dictTypeService.deleteBatchDictTypeByIds(ids));
    }


    /**
     * 加载字典列表树
     */
    @GetMapping("/treeData")
    public Result treeData() {
        List<Ztree> ztrees = dictTypeService.selectDictTree(new DictTypeQuery());
        return Result.success(ztrees);
    }
}
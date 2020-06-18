package com.yk.web.controller.system;

import com.yk.common.dto.Result;
import com.yk.system.model.pojo.ActionLog;
import com.yk.system.model.query.ActionLogQuery;
import com.yk.system.service.ActionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.List;
import com.yk.common.dto.DataTablesViewPage;

/**
 * @program: YK-Platform
 * @description: 操作日志controller
 * @author: YuKai Fan
 * @create: 2020-06-18 14:43:00
 **/
@RestController
@RequestMapping("/api/system/actionLog")
public class ActionLogController {
    @Autowired
    private ActionLogService actionLogService;

    /**
     * 获取操作日志集合
     * @param start
     * @param pageSize
     * @param actionLogQuery
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("system:actionLog:list")
    public Result listActionLogs(@RequestParam(value = "start", defaultValue = "0") int start,
                               @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                               ActionLogQuery actionLogQuery) {
        List<ActionLog> list = actionLogService.listActionLogs(start, pageSize, actionLogQuery);
        return Result.success("获取成功", new DataTablesViewPage<>(list));
    }

    /**
     * 新增操作日志
     * @param actionLog
     * @return
     */
    @PostMapping("/addActionLog")
    @RequiresPermissions("system:actionLog:add")
    public Result addActionLog(@RequestBody ActionLog actionLog) {
        return Result.response(actionLogService.insertActionLog(actionLog));
    }

    /**
     * 修改操作日志
     * @param actionLog
     * @return
     */
    @PutMapping("/editActionLog")
    @RequiresPermissions("system:actionLog:edit")
    public Result editActionLog(@RequestBody ActionLog actionLog) {
        return Result.response(actionLogService.updateActionLog(actionLog));
    }
    /**
     * 根据id删除操作日志
     * @param id
     * @return
     */
    @DeleteMapping("/deleteActionLogById/{id}")
    @RequiresPermissions("system:actionLog:delete")
    public Result deleteActionLogById(@PathVariable("id") String id) {
        return Result.response(actionLogService.deleteActionLogById(id));
    }

    /**
     * 批量删除操作日志
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteBatchActionLogByIds/{ids}")
    @RequiresPermissions("system:actionLog:delete")
    public Result deleteBatchActionLogByIds(@PathVariable("ids") List<String> ids) {
        return Result.response(actionLogService.deleteBatchActionLogByIds(ids));
    }

}
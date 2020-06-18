package com.yk.system.service;

import java.util.List;

import com.yk.system.model.pojo.ActionLog;
import com.yk.system.model.query.ActionLogQuery;

/**
 * 操作日志Service接口
 *
 * @author YuKai Fan
 * @create 2020-06-18 14:43:00
 */
public interface ActionLogService {
    /**
     * 新增操作日志
     * @param actionLog 操作日志
     * @return
     */
    int insertActionLog(ActionLog actionLog);

    /**
     * 批量新增操作日志
     * @param list
     */
    int insertActionLogBatch(List<ActionLog> list);

    /**
     * 更新操作日志
     * @param actionLog
     * @return
     */
    int updateActionLog(ActionLog actionLog);

    /**
     * 根据id删除操作日志
     * @param id
     * @return
     */
    int deleteActionLogById(String id);

    /**
     * 批量删除操作日志
     * @param ids
     * @return
     */
    int deleteBatchActionLogByIds(List<String> ids);

    /**
     * 根据id真删除操作日志
     * @param id
     * @return
     */
    int deleteActionLogRealById(String id);

    /**
     * 批量真删除操作日志
     * @param ids
     * @return
     */
    int deleteBatchActionLogRealByIds(List<String> ids);

    /**
     * 根据id获取操作日志
     * @param id
     * @return
     */
    ActionLog getActionLogById(String id);

    /**
     * 查询操作日志集合
     * @param actionLogQuery
     * @return
     */
    List<ActionLog> listActionLogs(ActionLogQuery actionLogQuery);

    /**
     * 查询操作日志集合(分页)
     * @param actionLogQuery
     * @return
     */
    List<ActionLog> listActionLogs(int start, int pageSize, ActionLogQuery actionLogQuery);

}

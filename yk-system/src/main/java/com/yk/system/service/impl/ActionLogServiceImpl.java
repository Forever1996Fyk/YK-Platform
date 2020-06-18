package com.yk.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.yk.common.util.AppUtils;
import com.yk.common.util.TimeUtils;
import com.yk.system.mapper.ActionLogMapper;
import com.yk.system.model.pojo.ActionLog;
import com.yk.system.model.query.ActionLogQuery;
import com.yk.system.service.ActionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @program: YK-Platform
 * @description: 操作日志service实现类
 * @author: YuKai Fan
 * @create: 2020-06-18 14:43:00
 **/
@Service
@Transactional
public class ActionLogServiceImpl implements ActionLogService {
    @Autowired
    private ActionLogMapper actionLogMapper;

    @Override
    public int insertActionLog(ActionLog actionLog) {
        actionLog.setId(AppUtils.randomId());
        actionLog.setStatus(1);
        actionLog.setCreateTime(TimeUtils.getCurrentDatetime());
        actionLog.setUpdateTime(TimeUtils.getCurrentDatetime());
        return actionLogMapper.insertActionLog(actionLog);
    }

    @Override
    public int insertActionLogBatch(List<ActionLog> list) {
        return actionLogMapper.insertActionLogBatch(list);
    }

    @Override
    public int updateActionLog(ActionLog actionLog) {
        actionLog.setUpdateTime(TimeUtils.getCurrentDatetime());
        return actionLogMapper.updateActionLog(actionLog);
    }

    @Override
    public int deleteActionLogById(String id) {
        return actionLogMapper.deleteActionLogById(id);
    }

    @Override
    public int deleteBatchActionLogByIds(List<String> ids) {
        return actionLogMapper.deleteBatchActionLogByIds(ids);
    }

    @Override
    public int deleteActionLogRealById(String id) {
        return actionLogMapper.deleteActionLogRealById(id);
    }

    @Override
    public int deleteBatchActionLogRealByIds(List<String> list) {
        return actionLogMapper.deleteBatchActionLogRealByIds(list);
    }

    @Override
    public ActionLog getActionLogById(String id) {
        return actionLogMapper.getActionLogById(id);
    }

    @Override
    public List<ActionLog> listActionLogs(ActionLogQuery actionLogQuery) {
        return actionLogMapper.listActionLogs(actionLogQuery);
    }

    @Override
    public List<ActionLog> listActionLogs(int start, int pageSize, ActionLogQuery actionLogQuery) {
        PageHelper.startPage(start, pageSize);
        return this.listActionLogs(actionLogQuery);
    }
    
}
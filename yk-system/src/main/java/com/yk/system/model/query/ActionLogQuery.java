package com.yk.system.model.query;

import com.yk.system.model.pojo.ActionLog;
import lombok.Data;

/**
 * 操作日志查询对象 tb_action_log
 * 
 * @author YuKai Fan
 * @date 2020-06-18 14:43:00
 */
@Data
public class ActionLogQuery extends ActionLog {

    /** 日志类型数组 */
    private Integer[] types;
}

package com.yk.system.model.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import lombok.Data;
import com.yk.common.entity.BaseEntity;

/**
 * 操作日志对象 tb_action_log
 * 
 * @author YuKai Fan
 * @create 2020-06-18 14:43:00
 */
@Data
public class ActionLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 日志标识 */
    private String id;
    /** 日志类型名称 */
    private String name;
    /** 日志类型 */
    private Integer type;
    /** 操作类别 */
    private Integer actionType;
    /** 操作ip */
    private String ipAddr;
    /** 请求URL */
    private String actionUrl;
    /** 操作地点 */
    private String actionLocation;
    /** 方法名称 */
    private String method;
    /** 请求方式 */
    private String requestMethod;
    /** 输入参数 */
    private String inputParam;
    /** 输出参数 */
    private String outputParam;
    /** 异常信息 */
    private String exceptionInfo;
    /** 备注 */
    private String remark;
    /** 状态:0  已禁用 1 正在使用 */
    private Integer status;
    /** 操作时间 */
    private String actionTime;
    /** 操作人员 */
    private String actionUserName;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("type", getType())
            .append("actionType", getActionType())
            .append("ipAddr", getIpAddr())
            .append("actionUrl", getActionUrl())
            .append("actionLocation", getActionLocation())
            .append("method", getMethod())
            .append("requestMethod", getRequestMethod())
            .append("inputParam", getInputParam())
            .append("outputParam", getOutputParam())
            .append("exceptionInfo", getExceptionInfo())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("actionTime", getActionTime())
            .toString();
    }
}

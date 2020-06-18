package com.yk.framework.aspectj;

import com.alibaba.fastjson.JSONObject;
import com.yk.common.annotation.ActionLog;
import com.yk.common.util.ServletUtils;
import com.yk.common.util.StringUtils;
import com.yk.framework.manager.AsyncTaskManager;
import com.yk.framework.manager.factory.AsyncTaskFactory;
import com.yk.framework.util.ShiroUtils;
import com.yk.system.model.pojo.SysUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @program: YK-Platform
 * @description: 操作日志记录AOP
 * @author: YuKai Fan
 * @create: 2020-06-18 15:13
 **/
@Aspect
@Component
public class ActionLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(ActionLogAspect.class);

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.yk.common.annotation.ActionLog)")
    public void actionLogPointCut() {}

    /**
     * 处理完请求后执行
     * @param joinPoint
     * @param jsonResult
     */
    @AfterReturning(pointcut = "actionLogPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "actionLogPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e)
    {
        handleLog(joinPoint, e, null);
    }



    protected void handleLog(final JoinPoint joinPoint, Exception e, Object jsonResult) {
        try {
            // 获取注解信息
            ActionLog log = getActionLogAnnotation(joinPoint);
            if (log == null) {
                return;
            }

            //获取当前用户
            SysUser currentSysUser = ShiroUtils.getCurrentSysUser();

            com.yk.system.model.pojo.ActionLog actionLog = new com.yk.system.model.pojo.ActionLog();
            actionLog.setStatus(1);
            //请求地址
            String ip = ShiroUtils.getCurrentIp();
            actionLog.setIpAddr(ip);
            // 返回参数
            actionLog.setOutputParam(JSONObject.toJSONString(jsonResult));

            // 请求url
            actionLog.setActionUrl(ServletUtils.getHttpServletRequest().getRequestURI());
            if (currentSysUser != null) {
                actionLog.setActionUserName(currentSysUser.getUserName());
            }

            if (e != null) {
                actionLog.setStatus(2);
                actionLog.setExceptionInfo(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            actionLog.setMethod(StringUtils.format("{}.{}", className, methodName));
            // 设置请求方式
            actionLog.setRequestMethod(ServletUtils.getHttpServletRequest().getMethod());
            // 处理设置注解上的参数
            getControllerMethodDescription(log, actionLog);

            Object[] args = joinPoint.getArgs();
            if (args != null) {
                String inputParams = StringUtils.format("{\"inputParams\":{}, \"requestParams\": {}}",  JSONObject.toJSONString(args), actionLog.getInputParam());
                actionLog.setInputParam(inputParams);
            }

            // 保存到数据库
            AsyncTaskManager.asyncManager().execute(AsyncTaskFactory.recordActionLog(actionLog));
        } catch (Exception ex) {
            // 记录本地异常日志
            logger.error(" ========== 前置通知异常 ========= ");
            logger.error("异常信息: [{}]", ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     * @param log
     * @param actionLog
     */
    private void getControllerMethodDescription(ActionLog log, com.yk.system.model.pojo.ActionLog actionLog) {
        // 设置action动作
        actionLog.setType(log.logType().ordinal());
        // 设置模块
        actionLog.setName(log.name());
        // 设置操作平台
        actionLog.setActionType(log.actionType().ordinal());
        // 是否需要保存请求参数
        if (log.isSaveRequestData()) {
            // 获取请求参数信息
            setRequestValue(actionLog);
        }
    }

    /**
     * 获取请求的参数，放到log中
     * @param actionLog
     */
    private void setRequestValue(com.yk.system.model.pojo.ActionLog actionLog) {
        Map<String, String[]> map = ServletUtils.getHttpServletRequest().getParameterMap();
        actionLog.setInputParam(JSONObject.toJSONString(map));
    }

    /**
     * 是否存在注解, 如果存在就获取
     * @param joinPoint
     * @return
     */
    private ActionLog getActionLogAnnotation(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(ActionLog.class);
        }
        return null;
    }

}
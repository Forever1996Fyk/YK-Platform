package com.yk.common.annotation;

import com.yk.common.enums.ActionTypeEnum;
import com.yk.common.enums.LogTypeEnum;

import java.lang.annotation.*;

/**
 * @program: YK-Platform
 * @description: 自定义注解, 操作日志记录
 * @author: YuKai Fan
 * @create: 2020-06-18 15:17
 **/
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ActionLog {

    /**
     * 日志模块名称
     * @return
     */
    String name() default "";

    /**
     * 日志类型
     * @return
     */
    LogTypeEnum logType() default LogTypeEnum.OTHER;

    /**
     * 操作类别
     * @return
     */
    ActionTypeEnum actionType() default ActionTypeEnum.OTHER;

    /**
     * 是否保存请求的参数
     * @return
     */
    boolean isSaveRequestData() default true;
}

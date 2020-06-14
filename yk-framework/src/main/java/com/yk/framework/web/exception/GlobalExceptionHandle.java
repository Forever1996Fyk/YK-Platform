package com.yk.framework.web.exception;

import com.yk.common.dto.Result;
import com.yk.common.exception.ParameterException;
import com.yk.common.exception.file.FileException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: YK-Platform
 * @description: 全局异常处理
 * @author: YuKai Fan
 * @create: 2020-06-08 09:27
 **/
@RestControllerAdvice
public class GlobalExceptionHandle {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandle.class);

    /**
     * 权限校验失败 如果请求为ajax返回json，普通请求跳转页面
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public Object handleAuthorizationException(HttpServletRequest request, AuthorizationException e) {
        logger.error("权限校验失败, [{}]", e.getMessage());
        //判断是否是ajax请求 todo
        if (true) {
            return Result.error(401, e.getMessage());
        }

        //跳转到未授权页面
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/unAuth");
        return modelAndView;
    }

    /**
     * 业务参数异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(ParameterException.class)
    public Result parameterException(HttpServletRequest request, ParameterException e) {
        logger.error("业务参数异常: [{}], 异常信息为: [{}]", e.getMessage(), e);
        return Result.error(902, e.getMessage());
    }

    /**
     * 文件异常
     * @author YuKai Fan
     * @param request
     * @param e
     * @return com.yk.common.dto.Result
     * @date 2020/6/13 23:07
     */
    @ExceptionHandler(FileException.class)
    public Result fileException(HttpServletRequest request, FileException e) {
        logger.error("文件异常: [{}], 异常信息为: [{}]", e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result notFount(RuntimeException e) {
        logger.error("运行时异常: [{}]", e);
        return Result.error("运行时异常:" + e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        logger.error("系统异常: [{}], 异常信息为: [{}]", e.getMessage(), e);
        return Result.error("服务器错误，请联系管理员");
    }
}
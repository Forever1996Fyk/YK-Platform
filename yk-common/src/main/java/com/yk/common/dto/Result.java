package com.yk.common.dto;

import lombok.Data;

/**
 * @program: YK-Platform
 * @description: 返回结果result
 * @author: YuKai Fan
 * @create: 2020-06-01 22:21
 **/
@Data
public class Result {
    //返回码
    private Integer code;
    //返回信息
    private String message;
    //返回结果
    private Object data;

    /**
     * 状态类型
     */
    public enum Type {
        /**
         * 成功
         */
        SUCCESS(200),
        /**
         * 警告
         */
        WARN(301),
        /**
         * 错误
         */
        ERROR(500),
        /**
         * 未授权
         */
        UNAUTHORIZED(401);

        private final int value;

        Type(int value) {
            this.value = value;
        }
    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result response(int rows) {
        return rows > 0 ? success():error();
    }

    public static Result response(int rows, Object data) {
        return rows > 0 ? success(data):error(data);
    }

    public static Result success() {
        return Result.success("操作成功");
    }

    public static Result success(Object data) {
        return Result.success("操作成功", data);
    }

    public static Result success(String msg) {
        return Result.success(msg, null);
    }

    public static Result success(String msg, Object data) {
        return new Result(Type.SUCCESS.value, msg, data);
    }

    public static Result error() {
        return Result.error("操作失败");
    }

    public static Result error(Object data) {
        return Result.error("操作失败", data);
    }

    public static Result error(String msg) {
        return Result.error(msg, null);
    }

    public static Result error(String msg, Object data) {
        return new Result(Type.ERROR.value, msg, data);
    }
}
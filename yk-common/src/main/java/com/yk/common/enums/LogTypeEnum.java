package com.yk.common.enums;

/**
 * @program: YK-Platform
 * @description: 操作日志类型
 * @author: YuKai Fan
 * @create: 2020-06-18 15:20
 **/
public enum LogTypeEnum {

    /**
     * 其它
     */
    OTHER,

    /**
     * 新增
     */
    INSERT,

    /**
     * 修改
     */
    UPDATE,

    /**
     * 删除
     */
    DELETE,

    /**
     * 授权
     */
    GRANT,

    /**
     * 导出
     */
    EXPORT,

    /**
     * 导入
     */
    IMPORT,

    /**
     * 生成代码
     */
    GENCODE,

    /**
     * 强退
     */
    FORCE,
}
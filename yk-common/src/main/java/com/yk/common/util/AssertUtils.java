package com.yk.common.util;

import com.yk.common.constant.UserConstants;
import com.yk.common.exception.ParameterException;

/**
 * @program: YK-Platform
 * @description: 业务验证工具类
 * @author: YuKai Fan
 * @create: 2020-06-09 20:31
 **/
public class AssertUtils {
    /**
     * 判断是否操作超级管理员账号
     * @param userId
     */
    public static void checkUserAllowed(String userId) {
        if(UserConstants.ADMIN_USER_ID.equals(userId)) {
            throw new ParameterException("无法操作超级管理员账号");
        }
    }
}
package com.yk.system.service;

import java.util.List;

import com.yk.system.model.pojo.LoginInfo;
import com.yk.system.model.query.LoginInfoQuery;

/**
 * 系统访问记录Service接口
 *
 * @author YuKai Fan
 * @create 2020-06-18 11:50:22
 */
public interface LoginInfoService {
    /**
     * 新增系统访问记录
     * @param loginInfo 系统访问记录
     * @return
     */
    int insertLoginInfo(LoginInfo loginInfo);

    /**
     * 批量新增系统访问记录
     * @param list
     */
    int insertLoginInfoBatch(List<LoginInfo> list);

    /**
     * 更新系统访问记录
     * @param loginInfo
     * @return
     */
    int updateLoginInfo(LoginInfo loginInfo);

    /**
     * 根据id删除系统访问记录
     * @param id
     * @return
     */
    int deleteLoginInfoById(String id);

    /**
     * 批量删除系统访问记录
     * @param ids
     * @return
     */
    int deleteBatchLoginInfoByIds(List<String> ids);

    /**
     * 根据id真删除系统访问记录
     * @param id
     * @return
     */
    int deleteLoginInfoRealById(String id);

    /**
     * 批量真删除系统访问记录
     * @param ids
     * @return
     */
    int deleteBatchLoginInfoRealByIds(List<String> ids);

    /**
     * 根据id获取系统访问记录
     * @param id
     * @return
     */
    LoginInfo getLoginInfoById(String id);

    /**
     * 查询系统访问记录集合
     * @param loginInfoQuery
     * @return
     */
    List<LoginInfo> listLoginInfos(LoginInfoQuery loginInfoQuery);

    /**
     * 查询系统访问记录集合(分页)
     * @param loginInfoQuery
     * @return
     */
    List<LoginInfo> listLoginInfos(int start, int pageSize, LoginInfoQuery loginInfoQuery);

}

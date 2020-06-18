package com.yk.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.yk.common.util.AppUtils;
import com.yk.common.util.TimeUtils;
import com.yk.system.mapper.LoginInfoMapper;
import com.yk.system.model.pojo.LoginInfo;
import com.yk.system.model.query.LoginInfoQuery;
import com.yk.system.service.LoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @program: YK-Platform
 * @description: 系统访问记录service实现类
 * @author: YuKai Fan
 * @create: 2020-06-18 11:50:22
 **/
@Service
@Transactional
public class LoginInfoServiceImpl implements LoginInfoService {
    @Autowired
    private LoginInfoMapper loginInfoMapper;

    @Override
    public int insertLoginInfo(LoginInfo loginInfo) {
        loginInfo.setId(AppUtils.randomId());
        loginInfo.setStatus(1);
        loginInfo.setCreateTime(TimeUtils.getCurrentDatetime());
        loginInfo.setUpdateTime(TimeUtils.getCurrentDatetime());
        return loginInfoMapper.insertLoginInfo(loginInfo);
    }

    @Override
    public int insertLoginInfoBatch(List<LoginInfo> list) {
        return loginInfoMapper.insertLoginInfoBatch(list);
    }

    @Override
    public int updateLoginInfo(LoginInfo loginInfo) {
        loginInfo.setUpdateTime(TimeUtils.getCurrentDatetime());
        return loginInfoMapper.updateLoginInfo(loginInfo);
    }

    @Override
    public int deleteLoginInfoById(String id) {
        return loginInfoMapper.deleteLoginInfoById(id);
    }

    @Override
    public int deleteBatchLoginInfoByIds(List<String> ids) {
        return loginInfoMapper.deleteBatchLoginInfoByIds(ids);
    }

    @Override
    public int deleteLoginInfoRealById(String id) {
        return loginInfoMapper.deleteLoginInfoRealById(id);
    }

    @Override
    public int deleteBatchLoginInfoRealByIds(List<String> list) {
        return loginInfoMapper.deleteBatchLoginInfoRealByIds(list);
    }

    @Override
    public LoginInfo getLoginInfoById(String id) {
        return loginInfoMapper.getLoginInfoById(id);
    }

    @Override
    public List<LoginInfo> listLoginInfos(LoginInfoQuery loginInfoQuery) {
        return loginInfoMapper.listLoginInfos(loginInfoQuery);
    }

    @Override
    public List<LoginInfo> listLoginInfos(int start, int pageSize, LoginInfoQuery loginInfoQuery) {
        PageHelper.startPage(start, pageSize);
        return this.listLoginInfos(loginInfoQuery);
    }
    
}
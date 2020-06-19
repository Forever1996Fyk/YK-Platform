package com.yk.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.yk.common.util.AppUtils;
import com.yk.common.util.TimeUtils;
import com.yk.system.mapper.UserOnlineMapper;
import com.yk.system.model.pojo.UserOnline;
import com.yk.system.model.query.UserOnlineQuery;
import com.yk.system.service.UserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @program: YK-Platform
 * @description: 在线用户记录service实现类
 * @author: YuKai Fan
 * @create: 2020-06-18 20:41:47
 **/
@Service
@Transactional
public class UserOnlineServiceImpl implements UserOnlineService {
    @Autowired
    private UserOnlineMapper userOnlineMapper;

    @Override
    public int insertUserOnline(UserOnline userOnline) {
        userOnline.setCreateTime(TimeUtils.getCurrentDatetime());
        userOnline.setUpdateTime(TimeUtils.getCurrentDatetime());
        return userOnlineMapper.insertUserOnline(userOnline);
    }

    @Override
    public int insertUserOnlineBatch(List<UserOnline> list) {
        return userOnlineMapper.insertUserOnlineBatch(list);
    }

    @Override
    public int updateUserOnline(UserOnline userOnline) {
        userOnline.setUpdateTime(TimeUtils.getCurrentDatetime());
        return userOnlineMapper.updateUserOnline(userOnline);
    }

    @Override
    public int deleteUserOnlineById(String id) {
        return userOnlineMapper.deleteUserOnlineById(id);
    }

    @Override
    public int deleteBatchUserOnlineByIds(List<String> ids) {
        return userOnlineMapper.deleteBatchUserOnlineByIds(ids);
    }

    @Override
    public int deleteUserOnlineRealById(String id) {
        return userOnlineMapper.deleteUserOnlineRealById(id);
    }

    @Override
    public int deleteBatchUserOnlineRealByIds(List<String> list) {
        return userOnlineMapper.deleteBatchUserOnlineRealByIds(list);
    }

    @Override
    public UserOnline getUserOnlineById(String id) {
        return userOnlineMapper.getUserOnlineById(id);
    }

    @Override
    public List<UserOnline> listUserOnlines(UserOnlineQuery userOnlineQuery) {
        return userOnlineMapper.listUserOnlines(userOnlineQuery);
    }

    @Override
    public List<UserOnline> listUserOnlines(int start, int pageSize, UserOnlineQuery userOnlineQuery) {
        PageHelper.startPage(start, pageSize);
        return this.listUserOnlines(userOnlineQuery);
    }

    @Override
    public List<UserOnline> listUserOnlineByExpired(String lastAccessTime) {
        return userOnlineMapper.listUserOnlineByExpired(lastAccessTime);
    }

}
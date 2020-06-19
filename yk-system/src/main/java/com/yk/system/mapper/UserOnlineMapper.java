package com.yk.system.mapper;

import java.util.List;
import com.yk.system.model.pojo.UserOnline;
import com.yk.system.model.query.UserOnlineQuery;
import org.apache.ibatis.annotations.Param;

/**
 * 在线用户记录Mapper接口
 * 
 * @author YuKai Fan
 * @create 2020-06-18 20:41:47
 */
public interface UserOnlineMapper {
    /**
     * 新增在线用户记录
     * @param userOnline 在线用户记录
     * @return
     */
    int insertUserOnline(UserOnline userOnline);

    /**
     * 批量新增在线用户记录
     * @param list
     */
    int insertUserOnlineBatch(@Param(value = "list") List<UserOnline> list);

    /**
     * 更新在线用户记录
     * @param userOnline
     * @return
     */
    int updateUserOnline(UserOnline userOnline);

    /**
     * 根据id删除在线用户记录
     * @param id
     * @return
     */
    int deleteUserOnlineById(String id);

    /**
     * 批量删除在线用户记录
     * @param ids
     * @return
     */
    int deleteBatchUserOnlineByIds(List<String> ids);

    /**
     * 根据id真删除在线用户记录
     * @param id
     * @return
     */
    int deleteUserOnlineRealById(String id);

    /**
     * 批量真删除在线用户记录
     * @param ids
     * @return
     */
    int deleteBatchUserOnlineRealByIds(List<String> ids);

    /**
     * 根据id获取在线用户记录
     * @param id
     * @return
     */
    UserOnline getUserOnlineById(String id);

    /**
     * 查询在线用户记录集合
     * @param userOnlineQuery
     * @return
     */
    List<UserOnline> listUserOnlines(UserOnlineQuery userOnlineQuery);

    /**
     * 查询过期会话集合
     * @param lastAccessTime
     * @return
     */
    List<UserOnline> listUserOnlineByExpired(String lastAccessTime);
}

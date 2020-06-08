package com.yk.system.mapper;

import java.util.List;
import com.yk.system.model.pojo.UserRole;
import org.apache.ibatis.annotations.Param;

/**
 * 用户角色关联Mapper接口
 * 
 * @author YuKai Fan
 * @create 2020-06-08 20:30:07
 */
public interface UserRoleMapper {
    /**
     * 新增用户角色关联
     * @param userRole 用户角色关联
     * @return
     */
    int insertUserRole(UserRole userRole);

    /**
     * 批量新增用户角色关联
     * @param list
     */
    int insertUserRoleBatch(@Param(value = "list") List<UserRole> list);

    /**
     * 根据id真删除用户角色关联
     * @param id
     * @return
     */
    int deleteUserRoleRealByUserId(String id);

    /**
     * 批量真删除用户角色关联
     * @param ids
     * @return
     */
    int deleteBatchUserRoleRealByUserIds(List<String> ids);

    /**
     * 根据id获取用户角色关联
     * @param id
     * @return
     */
    UserRole getUserRoleById(String id);

    /**
     * 根据角色id获取角色使用数量
     * @param roleId
     * @return
     */
    int countUserRoleByRoleId(String roleId);

    /**
     * 批量取消授权用户角色
     * @param roleId
     * @param userIds
     * @return
     */
    int delUserRoleInfos(@Param("roleId") String roleId, @Param("userIds") String[] userIds);
}

package com.yk.web.controller.system;

import com.yk.common.annotation.ActionLog;
import com.yk.common.dto.Result;
import com.yk.common.enums.LogTypeEnum;
import com.yk.common.enums.OnlineStatus;
import com.yk.framework.shiro.session.OnlineSession;
import com.yk.framework.shiro.session.OnlineSessionDAO;
import com.yk.framework.util.ShiroUtils;
import com.yk.system.model.pojo.UserOnline;
import com.yk.system.model.query.UserOnlineQuery;
import com.yk.system.service.UserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.util.List;
import com.yk.common.dto.DataTablesViewPage;

/**
 * @program: YK-Platform
 * @description: 在线用户记录controller
 * @author: YuKai Fan
 * @create: 2020-06-18 20:41:47
 **/
@RestController
@RequestMapping("/api/system/userOnline")
public class UserOnlineController {
    @Autowired
    private UserOnlineService userOnlineService;
    @Autowired
    private OnlineSessionDAO onlineSessionDAO;

    /**
     * 获取在线用户记录集合
     * @param start
     * @param pageSize
     * @param userOnlineQuery
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("system:userOnline:list")
    public Result listUserOnlines(@RequestParam(value = "start", defaultValue = "0") int start,
                               @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                               UserOnlineQuery userOnlineQuery) {
        List<UserOnline> list = userOnlineService.listUserOnlines(start, pageSize, userOnlineQuery);
        return Result.success("获取成功", new DataTablesViewPage<>(list));
    }

    /**
     * 强退用户
     * @param sessionId
     * @return
     */
    @ActionLog(name = "在线用户", logType = LogTypeEnum.FORCE)
    @RequiresPermissions("monitor:online:forceLogout")
    @PostMapping("/forceLogout/{sessionId}")
    public Result forceLogout(@PathVariable("sessionId") String sessionId) {
        UserOnline userOnline = userOnlineService.getUserOnlineById(sessionId);
        if (sessionId.equals(ShiroUtils.getSessionId())) {
            return Result.error("当前登录用户无法强退");
        }
        if (userOnline == null) {
            return Result.error("用户已下线");
        }
        OnlineSession onlineSession = (OnlineSession) onlineSessionDAO.readSession(userOnline.getSessionId());
        if (onlineSession == null) {
            return Result.error("用户已下线");
        }
        onlineSession.setStatus(OnlineStatus.OFF_LINE);
        onlineSessionDAO.update(onlineSession);
        userOnline.setStatus(OnlineStatus.OFF_LINE);
        userOnlineService.updateUserOnline(userOnline);
        return Result.success();
    }

    /**
     * 批量强退用户
     * @param sessionIds
     * @return
     */
    @ActionLog(name = "在线用户", logType = LogTypeEnum.FORCE)
    @RequiresPermissions("monitor:online:forceLogout")
    @PostMapping("/batchForceLogout/{sessionIds}")
    public Result batchForceLogout(@PathVariable("sessionIds") String[] sessionIds) {
        for (String sessionId : sessionIds) {
            UserOnline userOnline = userOnlineService.getUserOnlineById(sessionId);
            if (sessionId.equals(ShiroUtils.getSessionId())) {
                return Result.error("当前登录用户无法强退");
            }
            if (userOnline == null) {
                return Result.error("用户已下线");
            }
            OnlineSession onlineSession = (OnlineSession) onlineSessionDAO.readSession(userOnline.getSessionId());
            if (onlineSession == null) {
                return Result.error("用户已下线");
            }
            onlineSession.setStatus(OnlineStatus.OFF_LINE);
            onlineSessionDAO.update(onlineSession);
            userOnline.setStatus(OnlineStatus.OFF_LINE);
            userOnlineService.updateUserOnline(userOnline);
        }
        return Result.success();
    }

    /**
     * 新增在线用户记录
     * @param userOnline
     * @return
     */
    @PostMapping("/addUserOnline")
    @RequiresPermissions("system:userOnline:add")
    public Result addUserOnline(@RequestBody UserOnline userOnline) {
        return Result.response(userOnlineService.insertUserOnline(userOnline));
    }

    /**
     * 修改在线用户记录
     * @param userOnline
     * @return
     */
    @PutMapping("/editUserOnline")
    @RequiresPermissions("system:userOnline:edit")
    public Result editUserOnline(@RequestBody UserOnline userOnline) {
        return Result.response(userOnlineService.updateUserOnline(userOnline));
    }
    /**
     * 根据id删除在线用户记录
     * @param id
     * @return
     */
    @DeleteMapping("/deleteUserOnlineById/{id}")
    @RequiresPermissions("system:userOnline:delete")
    public Result deleteUserOnlineById(@PathVariable("id") String id) {
        return Result.response(userOnlineService.deleteUserOnlineById(id));
    }

    /**
     * 批量删除在线用户记录
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteBatchUserOnlineByIds/{ids}")
    @RequiresPermissions("system:userOnline:delete")
    public Result deleteBatchUserOnlineByIds(@PathVariable("ids") List<String> ids) {
        return Result.response(userOnlineService.deleteBatchUserOnlineByIds(ids));
    }

}
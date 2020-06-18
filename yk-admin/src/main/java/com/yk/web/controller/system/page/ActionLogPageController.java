package com.yk.web.controller.system.page;

import com.yk.system.service.ActionLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: YK-Platform
 * @description: 操作日志页面跳转controller
 * @author: YuKai Fan
 * @create: 2020-06-02 21:54
 **/
@Controller
@RequestMapping("/system/actionLog")
public class ActionLogPageController {
    private String prefix = "monitor/actionLog";
    @Autowired
    private ActionLogService actionLogService;

    /**
     * 跳转操作日志首页
     * @return
     */
    @GetMapping()
    @RequiresPermissions("system:actionLog:view")
    public String actionLog() {
        return prefix + "/actionLog";
    }

    /**
     * 跳转新增操作日志页面
     * @return
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 跳转 操作日志详情页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/detail/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        model.addAttribute("actionLog", actionLogService.getActionLogById(id));
        return prefix + "/detail";
    }

}
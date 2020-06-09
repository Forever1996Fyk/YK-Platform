package com.yk.web.controller.system.page;

import com.yk.system.service.DictDataService;
import com.yk.common.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: YK-Platform
 * @description: 字典数据页面跳转controller
 * @author: YuKai Fan
 * @create: 2020-06-02 21:54
 **/
@Controller
@RequestMapping("/system/dict/data")
public class DictDataPageController {
    private String prefix = "system/dict/data";
    @Autowired
    private DictDataService dictDataService;

    /**
     * 跳转字典数据首页
     * @return
     */
    @GetMapping()
    @RequiresPermissions("system:dict:view")
    public String dict() {
        return prefix + "/data";
    }

    /**
     * 跳转新增字典数据页面
     * @return
     */
    @GetMapping("/add/{dictType}")
    public String add(@PathVariable("dictType") String dictType, Model model) {
        model.addAttribute("dictType", dictType);
        return prefix + "/add";
    }

    /**
     * 跳转修改字典数据页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        model.addAttribute("dictData", dictDataService.getDictDataById(id));
        return prefix + "/edit";
    }

}
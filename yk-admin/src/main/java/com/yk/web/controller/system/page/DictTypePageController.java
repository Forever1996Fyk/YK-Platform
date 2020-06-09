package com.yk.web.controller.system.page;

import com.yk.system.model.query.DictTypeQuery;
import com.yk.system.service.DictTypeService;
import com.yk.common.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: YK-Platform
 * @description: 字典类型页面跳转controller
 * @author: YuKai Fan
 * @create: 2020-06-02 21:54
 **/
@Controller
@RequestMapping("/system/dict/type")
public class DictTypePageController {
    private String prefix = "system/dict/type";
    @Autowired
    private DictTypeService dictTypeService;

    /**
     * 跳转字典类型首页
     *
     * @return
     */
    @GetMapping()
    @RequiresPermissions("system:dict:view")
    public String dict() {
        return prefix + "/type";
    }

    /**
     * 跳转新增字典类型页面
     *
     * @return
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 跳转修改字典类型页面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        model.addAttribute("dictType", dictTypeService.getDictTypeById(id));
        return prefix + "/edit";
    }

    /**
     * 查询字典详细
     * @param dictId
     * @param model
     * @return
     */
    @RequiresPermissions("system:dict:list")
    @GetMapping("/detail/{dictId}")
    public String detail(@PathVariable("dictId") String dictId, Model model) {
        model.addAttribute("dict", dictTypeService.getDictTypeById(dictId));
        model.addAttribute("dictList", dictTypeService.listDictTypes(new DictTypeQuery()));
        return "system/dict/data/data";
    }

}
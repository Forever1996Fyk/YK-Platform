package com.yk.generator.controller;

import com.yk.generator.model.pojo.GenTable;
import com.yk.generator.service.GenTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: YK-Platform
 * @description: 代码生成页面controller
 * @author: YuKai Fan
 * @create: 2020-06-03 17:00
 **/
@Controller
@RequestMapping("/tool/gen")
public class GenPageController {
    private String prefix = "tool/gen";
    @Autowired
    private GenTableService genTableService;

    /**
     * 跳转到代码生成页面
     *
     * @return
     */
    @GetMapping()
    public String gen() {
        return prefix + "/gen";
    }

    /**
     * 导入表页面
     *
     * @return
     */
    @GetMapping("/importTable")
    public String importTable() {
        return prefix + "/importTable";
    }

    /**
     * 修改代码生成业务
     */
    @GetMapping("/edit/{tableId}")
    public String edit(@PathVariable("tableId") String tableId, Model model) {
        GenTable table = genTableService.getGenTableById(tableId);
        model.addAttribute("table", table);
        return prefix + "/edit";
    }
}
package com.yk.generator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    /**
     * 跳转到代码生成页面
     * @return
     */
    @GetMapping()
    public String gen() {
        return prefix + "/gen";
    }
}
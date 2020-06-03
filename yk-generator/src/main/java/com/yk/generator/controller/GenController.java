package com.yk.generator.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: YK-Platform
 * @description: 代码生成controller
 * @author: YuKai Fan
 * @create: 2020-06-03 15:33
 **/
@RestController
@RequestMapping("/api/gen")
public class GenController {

    /**
     * 生成代码
     * @param tableName
     * @param response
     */
    @PostMapping("/genCode/{tableName}")
    public void genCode(@PathVariable("tableName") String tableName, HttpServletResponse response) {

    }
}
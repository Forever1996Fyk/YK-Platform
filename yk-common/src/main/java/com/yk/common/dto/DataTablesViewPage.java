package com.yk.common.dto;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * @program: YK-iSystem
 * @description: 数据表格视图分页
 * @author: YuKai Fan
 * @create: 2020-06-02 20:12
 **/
@Data
public class DataTablesViewPage<T> {
    //封装的数据
    private List<T> rows;
    //数据总数
    private long total;

    public DataTablesViewPage(List<T> data) {
        if (data instanceof Page) {
            PageInfo a = new PageInfo(data);
            this.rows = a.getList();
            this.total = a.getTotal();
        }
    }
}
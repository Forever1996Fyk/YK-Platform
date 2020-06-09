package com.yk.framework.web.service;

import com.yk.system.model.pojo.DictData;
import com.yk.system.service.DictDataService;
import com.yk.system.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: YK-Platform
 * @description: 字典服务
 * @author: YuKai Fan
 * @create: 2020-06-09 15:26
 **/
@Service("dict")
public class DictService {
    @Autowired
    private DictTypeService dictTypeService;
    @Autowired
    private DictDataService dictDataService;

    /**
     * 根据字典类型获取字典数据信息
     *
     * @param dictType
     * @return
     */
    public List<DictData> getType(String dictType) {
        return dictTypeService.listDictDataByType(dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String getLabel(String dictType, String dictValue) {
        return "";
    }
}
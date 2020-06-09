package com.yk.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.yk.common.util.AppUtils;
import com.yk.common.util.TimeUtils;
import com.yk.system.mapper.DictDataMapper;
import com.yk.system.model.pojo.DictData;
import com.yk.system.model.query.DictDataQuery;
import com.yk.system.service.DictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @program: YK-Platform
 * @description: 字典数据service实现类
 * @author: YuKai Fan
 * @create: 2020-06-09 11:14:10
 **/
@Service
@Transactional
public class DictDataServiceImpl implements DictDataService {
    @Autowired
    private DictDataMapper dictDataMapper;

    @Override
    public int insertDictData(DictData dictData) {
        dictData.setId(AppUtils.randomId());
        dictData.setStatus(1);
        dictData.setCreateTime(TimeUtils.getCurrentDatetime());
        dictData.setUpdateTime(TimeUtils.getCurrentDatetime());
        return dictDataMapper.insertDictData(dictData);
    }

    @Override
    public int insertDictDataBatch(List<DictData> list) {
        return dictDataMapper.insertDictDataBatch(list);
    }

    @Override
    public int updateDictData(DictData dictData) {
        dictData.setUpdateTime(TimeUtils.getCurrentDatetime());
        return dictDataMapper.updateDictData(dictData);
    }

    @Override
    public int deleteDictDataById(String id) {
        return dictDataMapper.deleteDictDataById(id);
    }

    @Override
    public int deleteBatchDictDataByIds(List<String> ids) {
        return dictDataMapper.deleteBatchDictDataByIds(ids);
    }

    @Override
    public int deleteDictDataRealById(String id) {
        return dictDataMapper.deleteDictDataRealById(id);
    }

    @Override
    public int deleteBatchDictDataRealByIds(List<String> list) {
        return dictDataMapper.deleteBatchDictDataRealByIds(list);
    }

    @Override
    public DictData getDictDataById(String id) {
        return dictDataMapper.getDictDataById(id);
    }

    @Override
    public List<DictData> listDictDatas(DictDataQuery dictDataQuery) {
        return dictDataMapper.listDictDatas(dictDataQuery);
    }

    @Override
    public List<DictData> listDictDatas(int start, int pageSize, DictDataQuery dictDataQuery) {
        PageHelper.startPage(start, pageSize);
        return this.listDictDatas(dictDataQuery);
    }
    
}
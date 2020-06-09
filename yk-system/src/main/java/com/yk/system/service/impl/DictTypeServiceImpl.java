package com.yk.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.yk.common.exception.ParameterException;
import com.yk.common.util.AppUtils;
import com.yk.common.util.CacheUtils;
import com.yk.common.util.StringUtils;
import com.yk.common.util.TimeUtils;
import com.yk.system.mapper.DictDataMapper;
import com.yk.system.mapper.DictTypeMapper;
import com.yk.system.model.pojo.DictData;
import com.yk.system.model.pojo.DictType;
import com.yk.system.model.query.DictDataQuery;
import com.yk.system.model.query.DictTypeQuery;
import com.yk.system.service.DictTypeService;
import com.yk.system.util.DictUtils;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;


/**
 * @program: YK-Platform
 * @description: 字典类型service实现类
 * @author: YuKai Fan
 * @create: 2020-06-09 11:14:10
 **/
@Service
@Transactional
public class DictTypeServiceImpl implements DictTypeService {
    @Autowired
    private DictTypeMapper dictTypeMapper;
    @Autowired
    private DictDataMapper dictDataMapper;
    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void init() {
        CacheUtils.initCache(cacheManager);
        List<DictType> dictTypes = dictTypeMapper.listDictTypes(new DictTypeQuery());
        dictTypes.forEach(dictType -> {
            DictDataQuery dictDataQuery = new DictDataQuery();
            dictDataQuery.setDictType(dictType.getDictType());
            List<DictData> dictDataList = dictDataMapper.listDictDatas(dictDataQuery);
            DictUtils.setDictCache(dictType.getDictType(), dictDataList);
        });
    }

    @Override
    public int insertDictType(DictType dictType) {
        dictType.setId(AppUtils.randomId());
        dictType.setStatus(1);
        dictType.setCreateTime(TimeUtils.getCurrentDatetime());
        dictType.setUpdateTime(TimeUtils.getCurrentDatetime());
        int row = dictTypeMapper.insertDictType(dictType);
        if (row > 0) {
            DictUtils.clearDictCache();
        }
        return row;
    }

    @Override
    public int insertDictTypeBatch(List<DictType> list) {
        int row = dictTypeMapper.insertDictTypeBatch(list);
        if (row > 0) {
            DictUtils.clearDictCache();
        }
        return row;
    }

    @Override
    public int updateDictType(DictType dictType) {
        DictType oldDictType = this.getDictTypeById(dictType.getId());
        dictDataMapper.updateDictDataType(oldDictType.getDictType(), dictType.getDictType());
        dictType.setUpdateTime(TimeUtils.getCurrentDatetime());
        int row = dictTypeMapper.updateDictType(dictType);
        if (row > 0) {
            DictUtils.clearDictCache();
        }
        return row;
    }

    @Override
    public int deleteDictTypeById(String id) {
        checkDictNameIsAssign(id);
        int row = dictTypeMapper.deleteDictTypeById(id);
        if (row > 0) {
            DictUtils.clearDictCache();
        }
        return row;
    }

    @Override
    public int deleteBatchDictTypeByIds(List<String> ids) {
        ids.forEach(id -> checkDictNameIsAssign(id));
        int row = dictTypeMapper.deleteBatchDictTypeByIds(ids);
        if (row > 0) {
            DictUtils.clearDictCache();
        }
        return row;
    }

    private void checkDictNameIsAssign(String id) {
        DictType dictType = this.getDictTypeById(id);
        DictDataQuery dictDataQuery = new DictDataQuery();
        dictDataQuery.setDictType(dictType.getDictType());
        List<DictData> dictData = dictDataMapper.listDictDatas(dictDataQuery);
        if (!CollectionUtils.isEmpty(dictData)) {
            throw new ParameterException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
        }
    }

    @Override
    public int deleteDictTypeRealById(String id) {
        return dictTypeMapper.deleteDictTypeRealById(id);
    }

    @Override
    public int deleteBatchDictTypeRealByIds(List<String> list) {
        return dictTypeMapper.deleteBatchDictTypeRealByIds(list);
    }

    @Override
    public DictType getDictTypeById(String id) {
        return dictTypeMapper.getDictTypeById(id);
    }

    @Override
    public List<DictType> listDictTypes(DictTypeQuery dictTypeQuery) {
        return dictTypeMapper.listDictTypes(dictTypeQuery);
    }

    @Override
    public List<DictType> listDictTypes(int start, int pageSize, DictTypeQuery dictTypeQuery) {
        PageHelper.startPage(start, pageSize);
        return this.listDictTypes(dictTypeQuery);
    }

    @Override
    public List<DictData> listDictDataByType(String dictType) {
        List<DictData> dictDataCaches = DictUtils.getDictCache(dictType);
        if (!CollectionUtils.isEmpty(dictDataCaches)) {
            return dictDataCaches;
        }
        DictDataQuery dictDataQuery = new DictDataQuery();
        dictDataQuery.setDictType(dictType);
        List<DictData> dictDataList = dictDataMapper.listDictDatas(dictDataQuery);
        if (!CollectionUtils.isEmpty(dictDataList)) {
            DictUtils.setDictCache(dictType, dictDataList);
        }
        return null;
    }

}
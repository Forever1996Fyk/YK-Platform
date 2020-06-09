package com.yk.system.service;

import java.util.List;

import com.yk.common.entity.Ztree;
import com.yk.system.model.pojo.DictData;
import com.yk.system.model.pojo.DictType;
import com.yk.system.model.query.DictTypeQuery;

/**
 * 字典类型Service接口
 *
 * @author YuKai Fan
 * @create 2020-06-09 11:14:10
 */
public interface DictTypeService {
    /**
     * 新增字典类型
     * @param dictType 字典类型
     * @return
     */
    int insertDictType(DictType dictType);

    /**
     * 批量新增字典类型
     * @param list
     */
    int insertDictTypeBatch(List<DictType> list);

    /**
     * 更新字典类型
     * @param dictType
     * @return
     */
    int updateDictType(DictType dictType);

    /**
     * 根据id删除字典类型
     * @param id
     * @return
     */
    int deleteDictTypeById(String id);

    /**
     * 批量删除字典类型
     * @param ids
     * @return
     */
    int deleteBatchDictTypeByIds(List<String> ids);

    /**
     * 根据id真删除字典类型
     * @param id
     * @return
     */
    int deleteDictTypeRealById(String id);

    /**
     * 批量真删除字典类型
     * @param ids
     * @return
     */
    int deleteBatchDictTypeRealByIds(List<String> ids);

    /**
     * 根据id获取字典类型
     * @param id
     * @return
     */
    DictType getDictTypeById(String id);

    /**
     * 查询字典类型集合
     * @param dictTypeQuery
     * @return
     */
    List<DictType> listDictTypes(DictTypeQuery dictTypeQuery);

    /**
     * 查询字典类型集合(分页)
     * @param dictTypeQuery
     * @return
     */
    List<DictType> listDictTypes(int start, int pageSize, DictTypeQuery dictTypeQuery);

    /**
     * 根据字典类型获取字典数据信息
     * @param dictType
     * @return
     */
    List<DictData> listDictDataByType(String dictType);

    /**
     * 加载字典树
     * @param dictType
     * @return
     */
    List<Ztree> selectDictTree(DictTypeQuery dictTypeQuery);
}

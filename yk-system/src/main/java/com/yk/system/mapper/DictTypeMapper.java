package com.yk.system.mapper;

import java.util.List;
import com.yk.system.model.pojo.DictType;
import com.yk.system.model.query.DictTypeQuery;
import org.apache.ibatis.annotations.Param;

/**
 * 字典类型Mapper接口
 * 
 * @author YuKai Fan
 * @create 2020-06-09 11:14:10
 */
public interface DictTypeMapper {
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
    int insertDictTypeBatch(@Param(value = "list") List<DictType> list);

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
}

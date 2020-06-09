package com.yk.system.mapper;

import java.util.List;
import com.yk.system.model.pojo.DictData;
import com.yk.system.model.query.DictDataQuery;
import org.apache.ibatis.annotations.Param;

/**
 * 字典数据Mapper接口
 * 
 * @author YuKai Fan
 * @create 2020-06-09 11:14:10
 */
public interface DictDataMapper {
    /**
     * 新增字典数据
     * @param dictData 字典数据
     * @return
     */
    int insertDictData(DictData dictData);

    /**
     * 批量新增字典数据
     * @param list
     */
    int insertDictDataBatch(@Param(value = "list") List<DictData> list);

    /**
     * 更新字典数据
     * @param dictData
     * @return
     */
    int updateDictData(DictData dictData);

    /**
     * 根据id删除字典数据
     * @param id
     * @return
     */
    int deleteDictDataById(String id);

    /**
     * 批量删除字典数据
     * @param ids
     * @return
     */
    int deleteBatchDictDataByIds(List<String> ids);

    /**
     * 根据id真删除字典数据
     * @param id
     * @return
     */
    int deleteDictDataRealById(String id);

    /**
     * 批量真删除字典数据
     * @param ids
     * @return
     */
    int deleteBatchDictDataRealByIds(List<String> ids);

    /**
     * 根据id获取字典数据
     * @param id
     * @return
     */
    DictData getDictDataById(String id);

    /**
     * 查询字典数据集合
     * @param dictDataQuery
     * @return
     */
    List<DictData> listDictDatas(DictDataQuery dictDataQuery);

    /**
     * 同步修改字典类型
     * @param oldDictType
     * @param newDictType
     */
    void updateDictDataType(@Param("oldDictType") String oldDictType, @Param("newDictType") String newDictType);
}

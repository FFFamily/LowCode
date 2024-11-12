package com.rcszh.lowcode.orm.mapper;

import com.rcszh.lowcode.orm.SqlFieldConfig;

import java.util.List;
import java.util.Map;

public interface SqlMapper {
    /**
     * 查询数据
     */
    <E> List<E> selectList();

    /**
     * 添加数据
     */
    public void insert(Map<String, Object> tableInfo);


    /**
     * 创建基础的模版数据表
     */
    public void updateTable(List<SqlFieldConfig> sqlFieldConfigs);


    /**
     * 更新单个数据
     * @param tableInfo 表数据
     */
    public void update(Map<String, Object> tableInfo);

    /**
     * 删除指定id数据
     * @param fieldId 表Id
     */
    public void deletedById(String fieldId);

    /**
     * @param fieldId 表id
     * @return 获取指定Id数据
     */
    public Map<String, Object> selectOneById(String fieldId);

    /**
     * 指定类型查询
     */
    public <T> T selectOneForObject( Class<T> tClass);
}

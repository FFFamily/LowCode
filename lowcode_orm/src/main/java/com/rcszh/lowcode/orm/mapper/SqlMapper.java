package com.rcszh.lowcode.orm.mapper;

import com.rcszh.lowcode.orm.SqlFieldConfig;
import com.rcszh.lowcode.orm.constants.SqlExecType;
import com.rcszh.lowcode.orm.params.SelectWrapper;

import java.util.List;
import java.util.Map;

public interface SqlMapper {
    /**
     * 查询数据列表
     * @param param 查询参数
     * @return 查询对象列表
     */
    List<Map<String, Object>> selectListToMap(SelectWrapper param);
    /**
     * 查询单个对象
     * @param param 查询参数
     * @return 查询对象
     */
    Map<String, Object> selectOneToMap(SelectWrapper param);
    /**
     * 根据Id查询数据
     * @param fieldId 表id
     * @return 获取指定Id数据
     */
    Map<String, Object> selectByIdToMap(String fieldId);
    /**
     * 添加数据
     * @param tableInfo 数据信息
     */
    void insertByMap(Map<String, Object> tableInfo);
    /**
     * 更新单个数据
     * @param tableInfo 表数据
     */
    void update(Map<String, Object> tableInfo);
    /**
     * 删除指定id数据
     * @param fieldId 表Id
     */
    void deleteById(String fieldId);


    /* ================ 以下为数据库表操作  ================**/

    /**
     * 更新表结构
     * @param sqlFieldConfigs 更新表配置
     */
    void updateTable(List<SqlFieldConfig> sqlFieldConfigs);
}

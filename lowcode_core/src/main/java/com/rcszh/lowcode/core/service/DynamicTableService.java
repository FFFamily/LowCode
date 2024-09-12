package com.rcszh.lowcode.core.service;

import com.rcszh.lowcode.core.entity.form.ViewFormConfig;
import com.rcszh.lowcode.orm.ORM;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态数据库服务类（业务对象）
 */
@Service
public class DynamicTableService {

    /**
     * @param tableName 业务对象数据库表名
     * @return 获取所有业务对象列表数据
     */
    public List<Map<String, Object>> getRealTableDataList(String tableName) {
        return ORM.orm().tableName(tableName).selectList();
    }

    /**
     * @param tableName 业务对象数据库表名
     * @return 获取所有业务对象列表数据
     */
    public List<Map<String, Object>> getRealTableDataListByConfig(List<ViewFormConfig> listViewFormConfigs,String tableName) {
        // 展示配置

        return ORM.orm().tableName(tableName).selectList();
    }
    /**
     * 添加业务对象数据
     * @param tableName 数据表
     * @param tableInfo 数据信息
     */
    public void addRealTableData(String tableName, HashMap<String, Object> tableInfo) {
        ORM.orm().tableName(tableName).insert(tableInfo);
    }

    /**
     * 删除业务对象
     * @param tableName 业务对象数据库表名
     */
    public void deleteDynamicTableData(String tableName,String fieldId) {
        ORM.orm().tableName(tableName).deletedById(fieldId);
    }

    /**
     * 获取业务对象
     * @param tableName 业务对象数据库表名
     * @param fieldId 业务对象数据库Id
     */
    public Map<String,Object> getDynamicTableDataById(String tableName, String fieldId) {
        return ORM.orm().tableName(tableName).selectOneById(fieldId);
    }

    /**
     * 更新业务对象
     * @param tableName 业务对象数据库表名
     * @param tableInfo 要更新的业务对象数据
     */
    public void updateDynamicTableData(String tableName, HashMap<String, Object> tableInfo) {
        ORM.orm().tableName(tableName).update(tableInfo);
    }
}

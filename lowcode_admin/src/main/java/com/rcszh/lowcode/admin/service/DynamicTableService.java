package com.rcszh.lowcode.admin.service;

import com.rcszh.lowcode.admin.dto.show.FindRequest;
import com.rcszh.lowcode.core.entity.view.ViewFormConfig;
import com.rcszh.lowcode.core.enums.action.FormActionTypeEnum;
import com.rcszh.lowcode.orm.ORM;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 动态数据库服务类（业务对象）
 */
@Service
public class DynamicTableService {
    public static final String ID_KEY = "id";

    /**
     * 操作真实库表数据：添加、更新、删除
     * @param tableName 库表名称
     * @param action 动作类型
     * @param actionValue 数据载体
     */
    public void doActionToDynamicTable(String tableName, String action, Map<String,Object> actionValue) {
        if (FormActionTypeEnum.INSERT.getType().equals(action)) {
            addDynamicTableData(tableName, actionValue);
        }else if (FormActionTypeEnum.UPDATE.getType().equals(action)) {
            updateDynamicTableData(tableName, actionValue);
        }else if (FormActionTypeEnum.DELETE.getType().equals(action)) {
            deleteDynamicTableData(tableName,actionValue.get(ID_KEY).toString());
        }else {
            throw new RuntimeException("错误的ROM执行类型");
        }
    }

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
    public void addDynamicTableData(String tableName, Map<String, Object> tableInfo) {
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
    public void updateDynamicTableData(String tableName, Map<String, Object> tableInfo) {
        ORM.orm().tableName(tableName).update(tableInfo);
    }

    /**
     * 查询数据
     */
    public Object getDynamicTableDataByFindQuery(FindRequest findRequest) {
        String tableName = findRequest.getTableName();
        List<String> tableFiled = findRequest.getTableFiled();
        ORM orm = ORM.orm().tableName(tableName).columns(tableFiled).where(findRequest.getFilterCondition());
        if (tableFiled.size() > 1){
            return orm.selectList();
        }else {
            return orm.selectOneForObject(Object.class);
        }
    }
}

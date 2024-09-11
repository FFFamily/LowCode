package com.rcszh.lowcode.core.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rcszh.lowcode.core.entity.form.FormTable;
import com.rcszh.lowcode.core.mapper.CoreDataSourceTableMapper;
import com.rcszh.lowcode.orm.ORM;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 数据表
 * 业务模型
 */
@Service
public class FormTableService {
    @Resource
    private CoreDataSourceTableMapper coreDataSourceTableMapper;
    @Resource
    private CoreDataSourceTableRuleConfigService coreDataSourceTableRuleConfigService;

    /**
     * 创建表单
     * @param formTable
     */
    public void createFormData(FormTable formTable) {
        coreDataSourceTableMapper.insert(formTable);
        ORM.orm().tableName(formTable.getTableName()).createTemplateTable();
    }



    /**
     * 删除业务模型
     */
    public void deleteById(String id) {
        coreDataSourceTableMapper.deleteById(id);
    }

    /**
     * 更新业务模型
     */
    public void update(FormTable formTable) {
        coreDataSourceTableMapper.updateById(formTable);
    }

    /**
     * 通过Id查询业务模型
     */
    public FormTable getTableById(String id) {
        return coreDataSourceTableMapper.selectById(id);
    }



    public Object getAllDataSource(Long sourceId) {
//        return coreDataSourceTableMapper.selectList(new LambdaQueryWrapper<FormTable>().eq(FormTable::getCodeDataSourceId, sourceId));
        return null;
    }



}

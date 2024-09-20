package com.rcszh.lowcode.core.service.form;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rcszh.lowcode.core.entity.form.FormTable;
import com.rcszh.lowcode.core.mapper.FormTableMapper;
import com.rcszh.lowcode.orm.ORM;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据表
 * 业务模型
 */
@Service
public class FormTableService {
    @Resource
    private FormTableMapper formTableMapper;


    /**
     * 创建数据表（首次生成表单）
     * @param formTable 表单数据
     */
    public void createFormTable(FormTable formTable) {
        formTableMapper.insert(formTable);
        ORM.orm().tableName(formTable.getTableName()).createTemplateTable();
    }

    /**
     * 删除业务模型
     */
    public void deleteById(String id) {
        formTableMapper.deleteById(id);
    }

    /**
     * 更新业务模型
     */
    public void update(FormTable formTable) {
        formTableMapper.updateById(formTable);
    }

    /**
     * 通过Id查询业务模型
     */
    public FormTable getTableById(String id) {
        return formTableMapper.selectById(id);
    }



    public Object getAllDataSource(Long sourceId) {
//        return coreDataSourceTableMapper.selectList(new LambdaQueryWrapper<FormTable>().eq(FormTable::getCodeDataSourceId, sourceId));
        return null;
    }


    public List<FormTable> getTableByFormId(String formId) {
         return formTableMapper.selectList(new LambdaQueryWrapper<FormTable>().eq(FormTable::getFormId, formId));
    }

    /**
     * 批量添加
     * @param formTables
     */
    public void batchSaveFormTable(List<FormTable> formTables) {
        formTables.forEach(formTable -> {formTableMapper.insert(formTable);});
    }

    public void batchUpdateFormTable(List<FormTable> formTables) {
        formTables.forEach(formTable -> {
            FormTable oldFormTable = formTableMapper.selectById(formTable.getId());
            if (oldFormTable == null) {
                throw new RuntimeException("业务对象表不存在");
            }
            if (!oldFormTable.getTableName().equals(formTable.getTableName())) {
                throw new RuntimeException("不能修改已创建的业务对象表信息");
            }
            if (!oldFormTable.getType().equals(formTable.getType())) {
                throw new RuntimeException("表类型无法变更");
            }
            formTableMapper.updateById(formTable);
        });
    }

    /**
     * 通过数据库表查询对应的表单库表信息
     */
    public FormTable getFormTableByTableName(String formTableName) {
        return formTableMapper.selectOne(new LambdaQueryWrapper<FormTable>().eq(FormTable::getTableName, formTableName));
    }

    /**
     * 获取所有的表单表
     */
    public List<FormTable> getAllFormTable() {
       return formTableMapper.selectList(null);
    }
}

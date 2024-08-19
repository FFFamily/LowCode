package com.rcszh.lowcode.core.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rcszh.framework.lowcode.entity.FromDataTable;
import com.rcszh.framework.lowcode.exceptions.BizException;
import com.rcszh.framework.lowcode.mapper.FromDataStructureMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class FromDataStructureService {
    @Resource
    private FromDataStructureMapper fromDataStructureMapper;

    /**
     * 根据名称查询表单
     */
    public FromDataTable findFromDataStructureByName(String name) {
        return fromDataStructureMapper.selectOne(new LambdaQueryWrapper<FromDataTable>().eq(FromDataTable::getName, name));
    }

    /**
     * 新增表单
     */
    public void saveOneFromDataStructure(FromDataTable fromDataTable) {
        FromDataTable fromDataTableByName = findFromDataStructureByName(fromDataTable.getName());
        if (fromDataTableByName != null) {
            throw new BizException("表单名称已存在");
        }
        fromDataStructureMapper.insert(fromDataTable);
    }
}

package com.rcszh.lowcode.core.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rcszh.lowcode.core.entity.data_source.CoreDataSourceTable;
import com.rcszh.lowcode.core.mapper.CoreDataSourceTableMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CoreDataSourceTableService {
    @Resource
    private CoreDataSourceTableMapper coreDataSourceTableMapper;

    public void createOneDataSourceTable(CoreDataSourceTable coreDataSourceTable) {
        coreDataSourceTableMapper.insert(coreDataSourceTable);
    }

    public Object getAllDataSource(Long sourceId) {
        return coreDataSourceTableMapper.selectList(new LambdaQueryWrapper<CoreDataSourceTable>().eq(CoreDataSourceTable::getTableName, sourceId));
    }
}

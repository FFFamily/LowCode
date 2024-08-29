package com.rcszh.lowcode.core.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rcszh.lowcode.core.entity.data_source.CoreDataSourceField;
import com.rcszh.lowcode.core.mapper.CoreDataSourceFieldMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CoreDataSourceFieldService {
    @Resource
    private CoreDataSourceFieldMapper coreDataSourceFieldMapper;

    public Object getAllDataSource(String tableId) {
        return coreDataSourceFieldMapper.selectList(new LambdaQueryWrapper<CoreDataSourceField>().eq(CoreDataSourceField::getTableId,tableId));
    }

    public void createOneDataSourceField(CoreDataSourceField coreDataSourceField) {
        coreDataSourceFieldMapper.insert(coreDataSourceField);
    }
}

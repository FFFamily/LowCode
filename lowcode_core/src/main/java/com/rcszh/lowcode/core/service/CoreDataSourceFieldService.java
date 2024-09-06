package com.rcszh.lowcode.core.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rcszh.lowcode.core.entity.data_source.CoreDataSourceField;
import com.rcszh.lowcode.core.mapper.CoreDataSourceFieldMapper;
import com.rcszh.lowcode.core.requests.FieldCreateRequest;
import com.rcszh.lowcode.core.utils.SchemaOptionUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CoreDataSourceFieldService {
    @Resource
    private CoreDataSourceFieldMapper coreDataSourceFieldMapper;

    public Object getAllDataSource(String tableId) {
        return coreDataSourceFieldMapper.selectList(new LambdaQueryWrapper<CoreDataSourceField>().eq(CoreDataSourceField::getTableId,tableId));
    }

    public void createOneDataSourceField(FieldCreateRequest request) {
        // 对当前字段进行封装 options
        String options = SchemaOptionUtil.getOptions(request.getType(),request);
        CoreDataSourceField coreDataSourceField = new CoreDataSourceField();
        BeanUtils.copyProperties(request,coreDataSourceField);
        coreDataSourceField.setOptions(options);
        coreDataSourceFieldMapper.insert(coreDataSourceField);
    }
}

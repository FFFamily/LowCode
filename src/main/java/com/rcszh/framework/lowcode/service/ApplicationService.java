package com.rcszh.framework.lowcode.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rcszh.framework.lowcode.entity.FromDataStructure;
import com.rcszh.framework.lowcode.exceptions.ServerException;
import com.rcszh.framework.lowcode.mapper.FromDataStructureMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    @Resource
    private FromDataStructureMapper fromDataStructureMapper;

    /**
     * 根据名称查询表单
     */
    public FromDataStructure findFromDataStructureByName(String name) {
        return fromDataStructureMapper.selectOne(new LambdaQueryWrapper<FromDataStructure>().eq(FromDataStructure::getName, name));
    }

    /**
     * 新增表单
     */
    public void saveOneFromDataStructure(FromDataStructure fromDataStructure) {
        FromDataStructure fromDataStructureByName = findFromDataStructureByName(fromDataStructure.getName());
        if (fromDataStructureByName != null) {
            throw new ServerException("表单名称已存在");
        }
        fromDataStructureMapper.insert(fromDataStructure);
    }
}

package com.rcszh.lowcode.core.service;

import com.rcszh.lowcode.core.entity.data_source.CoreDataSource;
import com.rcszh.lowcode.core.mapper.CoreDataSourceMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoreDataSourceService {
    @Resource
    private CoreDataSourceMapper coreDataSourceMapper;


    /**
     * 获取所有的数据源
     * @return
     */
    public List<CoreDataSource> getAllDataSource() {
        return coreDataSourceMapper.selectList(null);
    }

    /**
     * 创建数据源
     * @param coreDataSource
     * @return
     */
    public void createOneDataSource(CoreDataSource coreDataSource) {
        coreDataSourceMapper.insert(coreDataSource);
    }
}

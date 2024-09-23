package com.rcszh.lowcode.core.service.data_source;

import com.rcszh.lowcode.core.entity.data_source.CoreDataSource;
import com.rcszh.lowcode.core.mapper.CoreDataSourceMapper;
import com.rcszh.lowcode.core.utils.DataSourceUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CoreDataSourceService {
    @Resource
    private CoreDataSourceMapper coreDataSourceMapper;


    /**
     * 获取所有的数据源
     */
    public List<CoreDataSource> getAllDataSource() {
        return coreDataSourceMapper.selectList(null);
    }

    /**
     * 创建数据源
     */
    @Transactional(rollbackFor = Exception.class)
    public void createOneDataSource(CoreDataSource coreDataSource) {
        DataSourceUtil.validateDataSource(coreDataSource.getUrl(), coreDataSource.getUsername(), coreDataSource.getPassword());
        coreDataSourceMapper.insert(coreDataSource);
    }
}

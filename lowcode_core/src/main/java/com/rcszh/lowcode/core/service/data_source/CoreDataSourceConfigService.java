package com.rcszh.lowcode.core.service.data_source;

import com.rcszh.lowcode.core.entity.data_source.CoreDataSourceConfig;
import com.rcszh.lowcode.core.entity.data_source.CoreDataSourceConfigLink;
import com.rcszh.lowcode.core.mapper.CoreDataSourceConfigLinkMapper;
import com.rcszh.lowcode.core.mapper.CoreDataSourceConfigMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoreDataSourceConfigService {
    @Resource
    private CoreDataSourceConfigMapper coreDataSourceConfigMapper;
    @Resource
    private CoreDataSourceConfigLinkMapper coreDataSourceConfigLinkMapper;
    /**
     * 创建数据源配置
     */
    public void createConfig(CoreDataSourceConfig coreDataSourceConfig, List<String> dataSourceIdList) {
        coreDataSourceConfigMapper.insert(coreDataSourceConfig);
        dataSourceIdList.forEach(dataSourceId -> {
            CoreDataSourceConfigLink link = new CoreDataSourceConfigLink();
            link.setDataSourceId(dataSourceId);
            link.setDataSourceConfigId(coreDataSourceConfig.getId());
            coreDataSourceConfigLinkMapper.insert(link);
        });
    }

    /**
     * 获取所有的数据源配置
     */
    public List<CoreDataSourceConfig> getAllConfig() {
        return coreDataSourceConfigMapper.selectList(null);
    }
}

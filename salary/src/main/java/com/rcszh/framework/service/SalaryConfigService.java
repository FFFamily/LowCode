package com.rcszh.framework.service;

import com.rcszh.framework.dto.SalaryConfigDto;
import com.rcszh.framework.entity.SalaryConfig;
import com.rcszh.framework.mapper.SalaryConfigMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryConfigService {
    @Resource
    private SalaryConfigMapper salaryConfigMapper;
    public void addConfig(SalaryConfigDto config) {
        for (SalaryConfig configConfig : config.getConfigs()) {
            salaryConfigMapper.insert(configConfig);
        }
    }

    public List<SalaryConfig> getConfigList() {
        return salaryConfigMapper.selectList(null);
    }
}

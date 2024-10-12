package com.rcszh.lowcode.salary.service;

import com.rcszh.lowcode.salary.dto.SalaryConfigDto;
import com.rcszh.lowcode.salary.entity.SalaryConfig;
import com.rcszh.lowcode.salary.entity.SalaryGroup;
import com.rcszh.lowcode.salary.mapper.SalaryConfigMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryConfigService {
    @Resource
    private SalaryConfigMapper salaryConfigMapper;
    @Resource
    private SalaryGroupService salaryGroupService;

    /**
     * TODO 要整合成批量
     * 添加
     */
    public void addConfig(SalaryConfigDto config) {
        for (SalaryConfig configConfig : config.getConfigs()) {
            if (configConfig.getId() == null) {
                salaryConfigMapper.insert(configConfig);
            }else {
                salaryConfigMapper.updateById(configConfig);
            }
        }
    }

    public List<SalaryConfig> getConfigList() {
        return salaryConfigMapper.selectList(null);
    }

    public SalaryConfigDto getSalaryConfig() {
        List<SalaryGroup> allGroup = salaryGroupService.getAllGroup();
        List<SalaryConfig> configList = getConfigList();
        SalaryConfigDto salaryConfigDto = new SalaryConfigDto();
        salaryConfigDto.setConfigs(configList);
        salaryConfigDto.setSalaryGroups(allGroup);
        return salaryConfigDto;
    }
}

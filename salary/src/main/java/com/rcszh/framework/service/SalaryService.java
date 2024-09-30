package com.rcszh.framework.service;

import com.rcszh.framework.dto.SalaryConfigDto;
import com.rcszh.framework.dto.SalaryQueryParamDto;
import com.rcszh.framework.entity.Salary;
import com.rcszh.framework.entity.SalaryConfig;
import com.rcszh.framework.mapper.SalaryConfigMapper;
import com.rcszh.framework.mapper.SalaryMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalaryService {
    @Resource
    private SalaryConfigService salaryConfigService;
    @Resource
    private SalaryMapper salaryMapper;

    public void generateSalaryList(SalaryQueryParamDto salaryQueryParamDto) {
        List<SalaryConfig> configs =  salaryConfigService.getConfigList();
        // TODO 去获取考勤信息
        List<Object> list = new ArrayList<Object>();
        for (Object o : list) {
            String userId = "";

        }
    }
}

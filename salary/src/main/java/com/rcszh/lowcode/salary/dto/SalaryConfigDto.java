package com.rcszh.lowcode.salary.dto;

import com.rcszh.lowcode.salary.entity.SalaryConfig;
import com.rcszh.lowcode.salary.entity.SalaryGroup;
import lombok.Data;

import java.util.List;

@Data
public class SalaryConfigDto {
    private List<SalaryGroup> salaryGroups;
    private List<SalaryConfig> configs;
}

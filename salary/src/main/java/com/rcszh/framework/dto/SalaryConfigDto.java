package com.rcszh.framework.dto;

import com.rcszh.framework.entity.SalaryConfig;
import lombok.Data;

import java.util.List;

@Data
public class SalaryConfigDto {
    private List<SalaryConfig> configs;
}

package com.rcszh.framework.controller;

import com.rcszh.framework.dto.SalaryConfigDto;
import com.rcszh.framework.service.SalaryConfigService;
import com.rcszh.lowcode.common.vo.BaseResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("salary/config")
public class SalaryConfigController {
    @Resource
    private SalaryConfigService salaryConfigService;
    @PostMapping("/add")
    public BaseResponse add(@RequestBody SalaryConfigDto config) {
        salaryConfigService.addConfig(config);
        return BaseResponse.success();
    }
}

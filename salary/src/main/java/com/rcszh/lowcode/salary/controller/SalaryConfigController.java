package com.rcszh.lowcode.salary.controller;

import com.rcszh.lowcode.salary.dto.SalaryConfigDto;
import com.rcszh.lowcode.salary.service.SalaryConfigService;
import com.rcszh.lowcode.common.vo.BaseResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get")
    public BaseResponse get() {
        return BaseResponse.success(salaryConfigService.getSalaryConfig());
    }
}

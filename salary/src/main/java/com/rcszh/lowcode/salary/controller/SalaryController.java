package com.rcszh.lowcode.salary.controller;

import com.rcszh.lowcode.salary.dto.SalaryQueryParamDto;
import com.rcszh.lowcode.salary.service.SalaryService;
import com.rcszh.lowcode.common.vo.BaseResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salary")
public class SalaryController {
    @Resource
    private SalaryService salaryService;
    @PostMapping("/list")
    public BaseResponse list(@RequestBody(required = false) SalaryQueryParamDto salaryQueryParamDto) {
        return BaseResponse.success(salaryService.generateSalaryList(salaryQueryParamDto));
    }
}

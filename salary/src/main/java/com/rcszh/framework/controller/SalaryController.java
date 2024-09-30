package com.rcszh.framework.controller;

import com.rcszh.framework.dto.SalaryQueryParamDto;
import com.rcszh.framework.service.SalaryService;
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
    public BaseResponse list(@RequestBody SalaryQueryParamDto salaryQueryParamDto) {
        salaryService.getSalaryList(salaryQueryParamDto);
        return BaseResponse.success();
    }
}

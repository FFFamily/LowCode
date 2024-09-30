package com.rcszh.framework.controller;

import com.rcszh.framework.entity.SalaryGroup;
import com.rcszh.framework.service.SalaryGroupService;
import com.rcszh.lowcode.common.vo.BaseResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("salary/config/group")
public class SalaryGroupController {
    @Resource
    private SalaryGroupService salaryGroupService;
    /**
     * 添加薪资模块
     */
    @PostMapping("/add")
    public BaseResponse add(@RequestBody SalaryGroup salaryGroup) {
        salaryGroupService.addGroup(salaryGroup);
        return BaseResponse.success();
    }
    /**
     * 添加薪资模块
     */
    @GetMapping("/list")
    public BaseResponse list() {
        return BaseResponse.success(salaryGroupService.getAllGroup());
    }
}

package com.rcszh.lowcode.admin.controller;

import com.rcszh.lowcode.admin.dto.LoginRequest;
import com.rcszh.lowcode.admin.vo.LoginUserInfoVo;
import com.rcszh.lowcode.common.vo.BaseResponse;
import com.rcszh.lowcode.core.entity.Application;
import com.rcszh.lowcode.core.service.ApplicationService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/application")
public class AdminApplicationController {
    @Resource
    private ApplicationService applicationService;

    @ResponseBody
    @GetMapping("/list")
    public BaseResponse applicationList(){
        return BaseResponse.success(applicationService.findAllApplication());
    }
    @ResponseBody
    @PostMapping("/save")
    public BaseResponse saveApplication(@RequestBody Application application){
        applicationService.saveOneApplication(application);
        return BaseResponse.success();
    }
}

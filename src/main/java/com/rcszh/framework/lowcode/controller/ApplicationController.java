package com.rcszh.framework.lowcode.controller;

import com.rcszh.framework.lowcode.entity.Application;
import com.rcszh.framework.lowcode.service.ApplicationService;
import com.rcszh.framework.lowcode.vo.BaseResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    @Resource
    private ApplicationService applicationService;

    @PostMapping("/save")
    @ResponseBody
    public BaseResponse createApplication(@RequestBody Application application) {
        return BaseResponse.success(null);
    }

//    @PostMapping("/list")
//    @ResponseBody
//    public BaseResponse<List<Application>> getAllApplication(){
//        return BaseResponse.success(applicationService.findAll());
//    }

}

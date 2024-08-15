package com.rcszh.framework.lowcode.controller;

import com.rcszh.framework.lowcode.entity.Application;
import com.rcszh.framework.lowcode.vo.BaseResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    @PostMapping("/save")
    @ResponseBody
    public BaseResponse createApplication(@RequestBody Application application) {
        return BaseResponse.success(null);
    }
}

package com.rcszh.framework.lowcode.controller;

import com.rcszh.framework.lowcode.vo.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fromDataStructure")
public class FromDataStructureController {
    public BaseResponse<Object> saveStructure(){

        return BaseResponse.success(null);
    }
}

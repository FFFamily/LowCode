package com.rcszh.framework.lowcode.controller;

import com.rcszh.framework.lowcode.entity.FromDataStructure;
import com.rcszh.framework.lowcode.service.FromDataStructureService;
import com.rcszh.framework.lowcode.vo.BaseResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fromDataStructure")
public class FromDataStructureController {
    @Resource
    private FromDataStructureService fromDataStructureService;

    @PostMapping("/save")
    @ResponseBody
    public BaseResponse<Object> saveStructure(@RequestBody FromDataStructure fromDataStructure) {
        fromDataStructureService.saveOneFromDataStructure(fromDataStructure);
        return BaseResponse.success(null);
    }
}

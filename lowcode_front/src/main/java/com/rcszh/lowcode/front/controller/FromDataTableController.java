//package com.rcszh.lowcode.front.controller;
//
//import com.rcszh.framework.lowcode.entity.FromDataTable;
//import com.rcszh.framework.lowcode.service.FromDataStructureService;
//import com.rcszh.framework.lowcode.vo.BaseResponse;
//import jakarta.annotation.Resource;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/fromDataStructure")
//public class FromDataTableController {
//    @Resource
//    private FromDataStructureService fromDataStructureService;
//
//    @PostMapping("/save")
//    @ResponseBody
//    public BaseResponse saveStructure(@RequestBody FromDataTable fromDataTable) {
//        fromDataStructureService.saveOneFromDataStructure(fromDataTable);
//        return BaseResponse.success(null);
//    }
//}

package com.rcszh.lowcode.admin.controller;

import com.rcszh.lowcode.common.vo.BaseResponse;
import com.rcszh.lowcode.core.entity.data_source.CoreDataSource;
import com.rcszh.lowcode.core.entity.data_source.CoreDataSourceField;
import com.rcszh.lowcode.core.requests.FieldCreateRequest;
import com.rcszh.lowcode.core.service.CoreDataSourceFieldService;
import com.rcszh.lowcode.core.service.CoreDataSourceService;
import com.rcszh.lowcode.core.service.CoreDataSourceTableService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/data_source/field")
public class CoreDataSourceFieldController {
    @Resource
    private CoreDataSourceFieldService coreDataSourceFieldService;
    @Resource
    private CoreDataSourceTableService coreDataSourceTableService;

    @ResponseBody
    @GetMapping("/list/{tableId}")
    public BaseResponse datasourceList(@PathVariable String tableId){
        return BaseResponse.success(coreDataSourceFieldService.getAllDataSource(tableId));
    }

    @ResponseBody
    @PostMapping("/save")
    public BaseResponse saveDatasource(@RequestBody FieldCreateRequest coreDataSourceField){
        coreDataSourceFieldService.createOneDataSourceField(coreDataSourceField);
        return BaseResponse.success();
    }



}

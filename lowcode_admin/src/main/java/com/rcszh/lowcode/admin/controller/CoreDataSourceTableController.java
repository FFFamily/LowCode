package com.rcszh.lowcode.admin.controller;

import com.rcszh.lowcode.common.vo.BaseResponse;
import com.rcszh.lowcode.core.entity.data_source.CoreDataSource;
import com.rcszh.lowcode.core.entity.data_source.CoreDataSourceTable;
import com.rcszh.lowcode.core.service.CoreDataSourceFieldService;
import com.rcszh.lowcode.core.service.CoreDataSourceService;
import com.rcszh.lowcode.core.service.CoreDataSourceTableService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/data_source/table")
public class CoreDataSourceTableController {
    @Resource
    private CoreDataSourceTableService coreDataSourceTableService;

    @ResponseBody
    @GetMapping("/list/{sourceId}")
    public BaseResponse datasourceList(@PathVariable Long sourceId){
        return BaseResponse.success(coreDataSourceTableService.getAllDataSource(sourceId));
    }

    @ResponseBody
    @PostMapping("/save")
    public BaseResponse saveDatasource(@RequestBody CoreDataSourceTable coreDataSourceTable){
        coreDataSourceTableService.createOneDataSourceTable(coreDataSourceTable);
        return BaseResponse.success();
    }

}

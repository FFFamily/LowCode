package com.rcszh.lowcode.admin.controller;

import com.rcszh.lowcode.common.vo.BaseResponse;
import com.rcszh.lowcode.core.entity.form.FormTable;
import com.rcszh.lowcode.core.service.form.FormTableService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/data_source/table")
public class CoreDataSourceTableController {
    @Resource
    private FormTableService formTableService;

    @ResponseBody
    @GetMapping("/list/{sourceId}")
    public BaseResponse datasourceList(@PathVariable Long sourceId){
        return BaseResponse.success(formTableService.getAllDataSource(sourceId));
    }


    @ResponseBody
    @PostMapping("/save")
    public BaseResponse saveDatasource(@RequestBody FormTable formTable){
//        formTableService.createOneDataSourceTable(formTable);
        return BaseResponse.success();
    }

}

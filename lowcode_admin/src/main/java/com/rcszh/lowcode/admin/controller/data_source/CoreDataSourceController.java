package com.rcszh.lowcode.admin.controller.data_source;

import com.rcszh.lowcode.common.vo.BaseResponse;
import com.rcszh.lowcode.core.entity.data_source.CoreDataSource;
import com.rcszh.lowcode.core.service.CoreDataSourceService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/data_source")
public class CoreDataSourceController {
    @Resource
    private CoreDataSourceService coreDataSourceService;


    @ResponseBody
    @GetMapping("/list")
    public BaseResponse datasourceList(){
        return BaseResponse.success(coreDataSourceService.getAllDataSource());
    }

    @ResponseBody
    @PostMapping("/save")
    public BaseResponse saveDatasource(@RequestBody CoreDataSource coreDataSource){
        coreDataSourceService.createOneDataSource(coreDataSource);
        return BaseResponse.success();
    }

}

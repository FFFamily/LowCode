package com.rcszh.lowcode.admin.controller.data_source;

import com.rcszh.lowcode.admin.dto.data_source.DataSourceConfigDto;
import com.rcszh.lowcode.common.vo.BaseResponse;
import com.rcszh.lowcode.core.entity.data_source.CoreDataSource;
import com.rcszh.lowcode.core.entity.data_source.CoreDataSourceConfig;
import com.rcszh.lowcode.core.service.data_source.CoreDataSourceConfigService;
import com.rcszh.lowcode.core.service.data_source.CoreDataSourceService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data_source")
public class CoreDataSourceController {
    @Resource
    private CoreDataSourceService coreDataSourceService;
    @Resource
    private CoreDataSourceConfigService coreDataSourceConfigService;


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

    @ResponseBody
    @GetMapping("/config/list")
    public BaseResponse datasourceConfigList(){
        return BaseResponse.success(coreDataSourceConfigService.getAllConfig());
    }

    @ResponseBody
    @PostMapping("/config/save")
    public BaseResponse saveDatasourceConfig(@RequestBody DataSourceConfigDto dataSourceConfigDto){
        coreDataSourceConfigService.createConfig(dataSourceConfigDto.getConfig(),dataSourceConfigDto.getDataSourceIdList());
        return BaseResponse.success();
    }

}

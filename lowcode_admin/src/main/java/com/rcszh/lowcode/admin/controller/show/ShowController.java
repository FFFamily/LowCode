package com.rcszh.lowcode.admin.controller.show;

import com.rcszh.lowcode.admin.dto.ShowListRequest;
import com.rcszh.lowcode.common.vo.BaseResponse;
import com.rcszh.lowcode.core.enums.ViewFormTypeEnum;
import com.rcszh.lowcode.core.service.DynamicTableService;
import com.rcszh.lowcode.core.service.view.ViewFormService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 展示层控制器
 */
@RestController
@RequestMapping("/api/table")
public class ShowController {
    @Resource
    private DynamicTableService dynamicTableService;
    @Resource
    private ViewFormService viewFormService;

    /**
     * 查看当前表单的对应类型的渲染配置
     */
    @ResponseBody
    @PostMapping("/config/{formId}/{type}")
    public BaseResponse show(@PathVariable String formId,@PathVariable String type){
        return BaseResponse.success(viewFormService.findConfigByType(formId,type));
    }

    @ResponseBody
    @PostMapping("/list/{tableName}")
    public BaseResponse list(@PathVariable String tableName){
        return BaseResponse.success(dynamicTableService.getRealTableDataList(tableName));
    }

    @ResponseBody
    @PostMapping("/add/{tableName}")
    public BaseResponse add(@PathVariable String tableName,@RequestBody HashMap<String,Object> tableInfo){
        dynamicTableService.addRealTableData(tableName,tableInfo);
        return BaseResponse.success();
    }

    @ResponseBody
    @GetMapping("/del/{tableName}/{fieldId}")
    public BaseResponse del(@PathVariable String tableName,@PathVariable String fieldId){
        dynamicTableService.deleteDynamicTableData(tableName,fieldId);
        return BaseResponse.success();
    }

    @ResponseBody
    @GetMapping("/get/{tableName}/{fieldId}")
    public BaseResponse get(@PathVariable String tableName,@PathVariable String fieldId){
        return BaseResponse.success(dynamicTableService.getDynamicTableDataById(tableName,fieldId));
    }

    @ResponseBody
    @PostMapping("/update/{tableName}")
    public BaseResponse update(@PathVariable String tableName,@RequestBody HashMap<String,Object> tableInfo){
        dynamicTableService.updateDynamicTableData(tableName,tableInfo);
        return BaseResponse.success();
    }

}

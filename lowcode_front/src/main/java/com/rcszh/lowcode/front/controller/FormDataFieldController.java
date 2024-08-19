package com.rcszh.lowcode.front.controller;

import com.rcszh.framework.lowcode.dto.BaseFormDataFieldDto;
import com.rcszh.framework.lowcode.entity.FormDataField;
import com.rcszh.framework.lowcode.service.FormDataFieldService;
import com.rcszh.framework.lowcode.vo.BaseResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 数据字段配置
 */
@RestController
@RequestMapping("/formDataField")
public class FormDataFieldController {
    @Resource
    private FormDataFieldService formDataFieldService;

    /**
     * 添加数据模型字段
     */
    @PostMapping("/create")
    public BaseResponse createField(@RequestBody FormDataField formDataField) {
        formDataFieldService.saveOneFormDataField(formDataField);
        return BaseResponse.success(null);
    }

    /**
     * 查询数据模型字段
     */
    @PostMapping("/list")
    public BaseResponse getFieldList(@RequestBody FormDataField formDataField) {
        return BaseResponse.success(formDataFieldService.getFieldQueryList(formDataField));
    }

    /**
     * 查询数据模型字段
     */
    @DeleteMapping("/del")
    public BaseResponse delField(@RequestBody BaseFormDataFieldDto formDataFieldDto) {
        return BaseResponse.success(formDataFieldService.delOneFormDataField(formDataFieldDto));
    }

    /**
     * 查询数据模型字段
     */
    @PostMapping("/info/{id}")
    public BaseResponse getFieldInfo(@PathVariable String id) {
        return BaseResponse.success(formDataFieldService.getOneFormDataDetailInfoById(id));
    }




}

package com.rcszh.lowcode.admin.controller.form;

import com.rcszh.lowcode.common.vo.BaseResponse;
import com.rcszh.lowcode.core.entity.dto.FormInfo;
import com.rcszh.lowcode.core.entity.view.ViewForm;
import com.rcszh.lowcode.core.entity.view.ViewFormConfig;
import com.rcszh.lowcode.core.service.form.FormService;
import com.rcszh.lowcode.core.service.view.ViewFormConfigService;
import com.rcszh.lowcode.core.service.view.ViewFormService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/form")
public class FormController {
    @Resource
    private FormService formService;
    /**
     * 创建表单
     */
    @ResponseBody
    @PostMapping("/create")
    public BaseResponse save(@RequestBody FormInfo formInfo){
        formService.createForm(formInfo);
        return BaseResponse.success();
    }
    /**
     * 表单列表
     */
    @ResponseBody
    @GetMapping("/all")
    public BaseResponse all(){
        return BaseResponse.success(formService.getAllForm());
    }
    /**
     * 发布
     */
    @ResponseBody
    @PostMapping("/release")
    public BaseResponse release(@RequestBody FormInfo formInfo){
        formService.releaseForm(formInfo);
        return BaseResponse.success();
    }
    /**
     * 获取表单详情
     */
    @ResponseBody
    @GetMapping("/getFormInfo/{formId}")
    public BaseResponse getFormInfo(@PathVariable String formId){
        return BaseResponse.success(formService.getFormInfoById(formId));
    }
}

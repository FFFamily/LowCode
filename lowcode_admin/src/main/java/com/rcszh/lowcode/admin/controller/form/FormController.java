package com.rcszh.lowcode.admin.controller.form;

import com.rcszh.lowcode.common.vo.BaseResponse;
import com.rcszh.lowcode.core.entity.form.FormInfo;
import com.rcszh.lowcode.core.entity.form.ViewFormConfig;
import com.rcszh.lowcode.core.service.form.FormService;
import com.rcszh.lowcode.core.service.view.ViewFormConfigService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/form")
public class FormController {
    @Resource
    private FormService formService;
    @Resource
    private ViewFormConfigService viewFormConfigService;

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
    /**
     * 获取表单视图
     */
    @ResponseBody
    @GetMapping("/getView/{formId}")
    public BaseResponse getViewFormInfo(@PathVariable String formId){
        return BaseResponse.success(formService.getViewFormInfo(formId));
    }
    /**
     * 获取表单视图
     */
    @ResponseBody
    @GetMapping("/getViewConfig/{viewFormId}")
    public BaseResponse getViewFormConfig(@PathVariable String viewFormId){
        return BaseResponse.success(formService.getViewFormConfigById(viewFormId));
    }

    /**
     * 获取表单视图
     */
    @ResponseBody
    @PostMapping("/updateViewConfig")
    public BaseResponse updateViewConfig(@RequestBody ViewFormConfig viewFormConfig){
        viewFormConfigService.updateViewFormConfig(viewFormConfig);
        return BaseResponse.success();
    }
}

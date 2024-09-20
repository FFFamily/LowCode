package com.rcszh.lowcode.admin.controller.form;

import com.rcszh.lowcode.common.vo.BaseResponse;
import com.rcszh.lowcode.core.entity.form.Form;
import com.rcszh.lowcode.core.entity.view.ViewForm;
import com.rcszh.lowcode.core.entity.view.ViewFormConfig;
import com.rcszh.lowcode.core.enums.form.FormStatusEnum;
import com.rcszh.lowcode.core.service.form.FormService;
import com.rcszh.lowcode.core.service.view.ViewFormConfigService;
import com.rcszh.lowcode.core.service.view.ViewFormService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/form/view")
public class ViewFormConfigController {
    @Resource
    private FormService formService;
    @Resource
    private ViewFormService viewFormService;
    @Resource
    private ViewFormConfigService viewFormConfigService;
    /**
     * 获取表单视图
     */
    @ResponseBody
    @GetMapping("/list/{formId}")
    public BaseResponse getViewFormInfo(@PathVariable String formId){
        Form form =  formService.getFormById(formId);
        if (!FormStatusEnum.PUBLISH.getStatus().equals(form.getFormStatus())){
            throw new RuntimeException("当前表单未发布，无法查看视图");
        }
        return BaseResponse.success(viewFormService.findAllByFormId(formId));
    }

    /**
     * 获取表单视图
     */
    @ResponseBody
    @GetMapping("/getViewConfig/{viewFormId}")
    public BaseResponse getViewFormConfig(@PathVariable String viewFormId){
        return BaseResponse.success(viewFormConfigService.findAllConfigById(viewFormId));
    }

    /**
     * 更新表单视图配置
     */
    @ResponseBody
    @PostMapping("/updateViewConfig")
    public BaseResponse updateViewConfig(@RequestBody ViewFormConfig viewFormConfig){
        viewFormConfigService.updateViewFormConfig(viewFormConfig);
        return BaseResponse.success();
    }

    /**
     * 新增表单视图
     */
    @ResponseBody
    @PostMapping("/addViewForm")
    public BaseResponse addViewForm(@RequestBody ViewForm viewForm){
        viewFormService.addViewForm(viewForm);
        return BaseResponse.success();
    }
}

package com.rcszh.lowcode.admin.controller.form;

import com.rcszh.lowcode.common.vo.BaseResponse;
import com.rcszh.lowcode.core.dto.view.ViewFormConfigDto;
import com.rcszh.lowcode.core.dto.view.ViewFormConfigFindDto;
import com.rcszh.lowcode.core.entity.form.Form;
import com.rcszh.lowcode.core.entity.view.ViewForm;
import com.rcszh.lowcode.core.entity.view.ViewFormConfig;
import com.rcszh.lowcode.core.enums.form.FormStatusEnum;
import com.rcszh.lowcode.core.service.form.FormService;
import com.rcszh.lowcode.core.service.view.ViewFormConfigService;
import com.rcszh.lowcode.core.service.view.ViewFormService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 视图
 */
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
     * 改变视图状态
     */
    @ResponseBody
    @GetMapping("/status/{viewFormId}/{status}")
    public BaseResponse changeViewStatus(@PathVariable String viewFormId,@PathVariable String status){
        return BaseResponse.success();
    }

    /**
     * 获取对应视图的所有表单配置
     */
    @ResponseBody
    @GetMapping("/getViewConfig/{viewFormId}/{parentType}")
    public BaseResponse getViewFormConfig(@PathVariable String viewFormId,@PathVariable String parentType){
        return BaseResponse.success(viewFormConfigService.findAllConfigById(
                ViewFormConfigFindDto.builder()
                        .viewFormId(viewFormId)
                        .viewConfigParentType(parentType)
                        .build()
                )
        );
    }
    /**
     * 更新表单视图配置
     */
    @ResponseBody
    @PostMapping("/updateViewConfig")
    public BaseResponse updateViewConfig(@RequestBody ViewFormConfigDto viewFormConfig){
        viewFormConfigService.updateViewFormConfig(viewFormConfig);
        return BaseResponse.success();
    }
    /**
     * 新增表单视图
     */
    @ResponseBody
    @PostMapping("/addViewForm")
    public BaseResponse addViewForm(@RequestBody ViewForm viewForm){
        String formId = viewForm.getFormId();
        Form form = formService.getFormById(formId);
        if (form == null) {
            throw new RuntimeException("对应表单不存在");
        }
        viewFormService.addViewForm(viewForm);
        return BaseResponse.success();
    }
}

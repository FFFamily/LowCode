package com.rcszh.lowcode.admin.controller.action;

import com.rcszh.lowcode.admin.service.AdminActionService;
import com.rcszh.lowcode.common.vo.BaseResponse;
import com.rcszh.lowcode.core.entity.dto.FormActionInfo;
import com.rcszh.lowcode.core.entity.form.Form;
import com.rcszh.lowcode.core.enums.form.FormStatusEnum;
import com.rcszh.lowcode.core.service.form.FormService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/form/action/condition")
public class FormConditionController {
    @Resource
    private AdminActionService actionService;
    @Resource
    private FormService formService;
    /**
     * 添加
     */
    @PostMapping("/add")
    public BaseResponse add(@RequestBody FormActionInfo formActionInfo) {
        actionService.addFormConditionByFirst(formActionInfo);
        return BaseResponse.success();
    }
    /**
     * 添加
     */
    @GetMapping("/all/{formId}")
    public BaseResponse all(@PathVariable String formId) {
        Form form =  formService.getFormById(formId);
        if (!FormStatusEnum.PUBLISH.getStatus().equals(form.getFormStatus())){
            throw new RuntimeException("当前表单未发布，无法进行动作配置");
        }
        return BaseResponse.success(actionService.getConditionListByFormId(formId));
    }
}

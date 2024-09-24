package com.rcszh.lowcode.admin.controller.form_rule;

import com.rcszh.lowcode.common.vo.BaseResponse;
import com.rcszh.lowcode.core.entity.form_rule.FormRule;
import com.rcszh.lowcode.core.service.form_rule.FormRuleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/form/rule")
public class FormRuleController {
    @Resource
    private FormRuleService formRuleService;

    /**
     * 表单列表
     */
    @ResponseBody
    @GetMapping("/all/{type}/{formId}")
    public BaseResponse all(@PathVariable String type, @PathVariable String formId) {
        return BaseResponse.success(formRuleService.getAllRuleByType(formId, type));
    }

    /**
     * 表单列表
     */
    @ResponseBody
    @PostMapping("/add/{type}")
    public BaseResponse add(@PathVariable String type,@RequestBody FormRule formRule) {
        formRuleService.addRule(type,formRule);
        return BaseResponse.success();
    }
}

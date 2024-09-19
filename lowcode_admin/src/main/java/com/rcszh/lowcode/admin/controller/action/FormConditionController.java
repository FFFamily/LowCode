package com.rcszh.lowcode.admin.controller.action;

import com.rcszh.lowcode.admin.service.AdminActionService;
import com.rcszh.lowcode.common.vo.BaseResponse;
import com.rcszh.lowcode.core.entity.dto.FormActionInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/form/action/condition")
public class FormConditionController {
    @Resource
    private AdminActionService actionService;
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
        return BaseResponse.success(actionService.getConditionListByFormId(formId));
    }
}

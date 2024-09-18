package com.rcszh.lowcode.admin.controller.action;

import com.rcszh.lowcode.admin.service.AdminActionService;
import com.rcszh.lowcode.common.vo.BaseResponse;
import com.rcszh.lowcode.core.entity.dto.FormActionInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

package com.rcszh.lowcode.admin.controller.action;

import com.rcszh.lowcode.admin.service.AdminActionService;
import com.rcszh.lowcode.common.vo.BaseResponse;
import com.rcszh.lowcode.core.entity.action.FormAction;
import com.rcszh.lowcode.core.entity.dto.FormActionInfo;
import com.rcszh.lowcode.core.service.action.FormConditionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/form/action")
public class FormActionController {
    @Resource
    private AdminActionService actionService;
    /**
     * 添加
     */
    @PostMapping("/add")
    public BaseResponse add(@RequestBody FormAction formAction) {
        actionService.addFormAction(formAction);
        return BaseResponse.success();
    }
}

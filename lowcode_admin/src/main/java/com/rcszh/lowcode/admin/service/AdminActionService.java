package com.rcszh.lowcode.admin.service;

import com.rcszh.lowcode.core.entity.action.FormAction;
import com.rcszh.lowcode.core.entity.action.FormCondition;
import com.rcszh.lowcode.core.entity.dto.FormActionInfo;
import com.rcszh.lowcode.core.mapper.action.FormActionMapper;
import com.rcszh.lowcode.core.service.action.FormActionConditionService;
import com.rcszh.lowcode.core.service.action.FormActionService;
import com.rcszh.lowcode.core.service.action.FormConditionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminActionService {
    @Resource
    private FormConditionService formConditionService;
    @Resource
    private FormActionService formActionService;
    @Resource
    private FormActionConditionService formActionConditionService;

    /**
     * 添加表单动作
     */
    public void addFormConditionByFirst(FormActionInfo formCondition) {
        // 添加动作触发条件
        String conditionId = formConditionService.addFormCondition(formCondition.getFormCondition());
        List<FormAction> formActions = formCondition.getFormActions();
        if (formActions != null && !formActions.isEmpty()) {
            List<String> actionIdList = formActions.stream().map(FormAction::getId).toList();
            // 关联触发条件和触发动作
            formActionConditionService.associateAction(conditionId,actionIdList);
        }
    }

    /**
     * 添加动作
     */
    public void addFormAction(FormAction formAction) {
        formActionService.addAction(formAction);
    }

    /**
     * 获取所有的动作条件场景
     */
    public List<FormCondition> getAllCondition() {
        return formConditionService.findAllCondition();
    }

    /**
     * 获取对应表单的动作条件
     */
    public List<FormCondition> getConditionListByFormId(String formId) {
        return formConditionService.findConditionByFormId(formId);
    }
}

package com.rcszh.lowcode.admin.service;

import com.rcszh.lowcode.core.dto.action.FormActionCreateDto;
import com.rcszh.lowcode.core.entity.action.FormAction;
import com.rcszh.lowcode.core.entity.action.FormActionCondition;
import com.rcszh.lowcode.core.entity.action.FormCondition;
import com.rcszh.lowcode.core.entity.dto.FormActionInfo;
import com.rcszh.lowcode.core.mapper.action.FormActionMapper;
import com.rcszh.lowcode.core.service.action.FormActionConditionService;
import com.rcszh.lowcode.core.service.action.FormActionService;
import com.rcszh.lowcode.core.service.action.FormConditionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
//        String conditionId = formConditionService.addFormCondition(formCondition.getFormCondition());
//        List<FormAction> formActions = formCondition.getFormActions();
//        if (formActions != null && !formActions.isEmpty()) {
//            List<String> actionIdList = formActions.stream().map(FormAction::getId).toList();
//            // 关联触发条件和触发动作
//            formActionConditionService.associateAction(conditionId,actionIdList);
//        }
    }

    /**
     * 添加动作
     */
    @Transactional(rollbackFor = Exception.class)
    public void addFormAction(FormActionCreateDto formActionCreateDto) {
        // 新增动作场景
        FormCondition formCondition = formActionCreateDto.getFormCondition();
        formConditionService.save(formCondition);
        // 新增具体动作
        List<FormAction> formActions = formActionCreateDto.getFormActions();
        formActionService.saveBatch(formActions);
        // TODO 这里需要存在一个检验循环动作问题
        // 新增中间关联表
        formActionConditionService.saveBatch(formActions.stream().map(item -> {
            FormActionCondition formActionCondition = new FormActionCondition();
            formActionCondition.setActionId(item.getId());
            formActionCondition.setConditionId(formCondition.getId());
            return formActionCondition;
        }).toList());
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

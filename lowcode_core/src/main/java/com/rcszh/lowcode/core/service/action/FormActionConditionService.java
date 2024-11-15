package com.rcszh.lowcode.core.service.action;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rcszh.lowcode.core.entity.action.FormActionCondition;
import com.rcszh.lowcode.core.entity.action.FormCondition;
import com.rcszh.lowcode.core.mapper.action.FormActionConditionMapper;
import com.rcszh.lowcode.core.mapper.action.FormConditionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormActionConditionService extends ServiceImpl<FormActionConditionMapper, FormActionCondition> {
    @Resource
    private FormActionConditionMapper formActionConditionMapper;

    /**
     * 添加
     */
    private void addCondition(FormActionCondition formActionCondition) {
        formActionConditionMapper.insert(formActionCondition);
    }

    /**
     * 列表
     */
    private List<FormActionCondition> findAllFormActionCondition() {
        return formActionConditionMapper.selectList(null);
    }

    /**
     * 添加关联关系
     * TODO 批量操作
     */
    public void associateAction(String conditionId, List<String> actionIdList) {
        actionIdList.stream().map(item -> {
            FormActionCondition formActionCondition = new FormActionCondition();
            formActionCondition.setConditionId(conditionId);
            formActionCondition.setActionId(item);
            return formActionCondition;
        }).forEach(item -> formActionConditionMapper.insert(item));
    }
}

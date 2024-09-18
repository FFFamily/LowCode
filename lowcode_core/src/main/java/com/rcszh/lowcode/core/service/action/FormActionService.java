package com.rcszh.lowcode.core.service.action;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rcszh.lowcode.core.entity.action.FormAction;
import com.rcszh.lowcode.core.entity.action.FormActionCondition;
import com.rcszh.lowcode.core.mapper.action.FormActionConditionMapper;
import com.rcszh.lowcode.core.mapper.action.FormActionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class FormActionService {
    @Resource
    private FormActionMapper formActionMapper;
    @Resource
    private FormActionConditionMapper formActionConditionMapper;
    public void addAction(FormAction formAction) {
        formActionMapper.insert(formAction);
    }

    /**
     *
     * @param formConditionList
     * @return Map{key:formConditionId,value:Action}
     */
    public HashMap<String, List<FormAction>> findActionByConditionToMap(List<String> formConditionList) {
        // 中间表数据
        List<FormActionCondition> formActionConditions = formActionConditionMapper.selectList(new LambdaQueryWrapper<FormActionCondition>()
                .in(FormActionCondition::getConditionId, formConditionList));
        HashMap<String, String> mapping = formActionConditions.stream().reduce(new HashMap<String, String>(), (prev, curr) -> {
            prev.put(curr.getActionId(), curr.getConditionId());
            return prev;
        }, (l, r) -> l);
        // 中间表对应的action配置
        return formActionMapper.selectList(new LambdaQueryWrapper<FormAction>()
                        .in(FormAction::getId, formActionConditions.stream().map(FormActionCondition::getActionId).toList()))
                .stream().reduce(new HashMap<String, List<FormAction>>(), (prev, curr) -> {
                    String formConditionId = mapping.get(curr.getId());
                    List<FormAction> list = prev.getOrDefault(formConditionId, new ArrayList<>());
                    list.add(curr);
                    prev.put(formConditionId, list);
                    return prev;
                }, (l, r) -> l);
    }
}

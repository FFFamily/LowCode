package com.rcszh.lowcode.core.service.action;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rcszh.lowcode.core.entity.action.FormAction;
import com.rcszh.lowcode.core.entity.action.FormActionCondition;
import com.rcszh.lowcode.core.entity.action.FormCondition;
import com.rcszh.lowcode.core.mapper.action.FormActionConditionMapper;
import com.rcszh.lowcode.core.mapper.action.FormActionMapper;
import com.rcszh.lowcode.core.mapper.action.FormConditionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class FormActionService extends ServiceImpl<FormActionMapper, FormAction> {
    @Resource
    private FormActionMapper formActionMapper;
    @Resource
    private FormActionConditionMapper formActionConditionMapper;

    /**
     * 添加动作
     */
    public void addAction(FormAction formAction) {
//        if (formAction.getAcResource().equals(formAction.getAcTarget())){
//            throw new RuntimeException("源表单不能和动作表单相同");
//        }
        formActionMapper.insert(formAction);
    }

    /**
     * 根据动作条件查询对应的动作
     * @return Map{key:formConditionId,value:Action}
     */
    public HashMap<String, List<FormAction>> findActionByConditionToMap(List<String> formConditionList) {
        // 中间表数据
        if (formConditionList == null || formConditionList.isEmpty()) {
            return new HashMap<>();
        }
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

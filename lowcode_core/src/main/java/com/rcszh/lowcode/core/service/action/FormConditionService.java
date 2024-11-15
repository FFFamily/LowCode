package com.rcszh.lowcode.core.service.action;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rcszh.lowcode.core.entity.action.FormCondition;
import com.rcszh.lowcode.core.mapper.action.FormConditionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormConditionService extends ServiceImpl<FormConditionMapper, FormCondition> {
    @Resource
    private FormConditionMapper formConditionMapper;


    /**
     * 根绝表单Id以及类型查询动作场景
     */
    public List<FormCondition> findConditionByFormIdAndType(String formId, String type) {
        return formConditionMapper.selectList(new LambdaQueryWrapper<FormCondition>()
                .eq(FormCondition::getFormId, formId)
                .eq(FormCondition::getCdType, type));
    }

    /**
     * 根绝表单Id查询动作场景
     */
    public List<FormCondition> findConditionByFormId(String formId) {
        return formConditionMapper.selectList(new LambdaQueryWrapper<FormCondition>().eq(FormCondition::getFormId, formId));
    }

    /**
     * 获取所有
     */
    public List<FormCondition> findAllCondition() {
        return formConditionMapper.selectList(null);
    }
}

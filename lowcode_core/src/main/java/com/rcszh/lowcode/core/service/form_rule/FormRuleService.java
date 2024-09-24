package com.rcszh.lowcode.core.service.form_rule;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rcszh.lowcode.core.entity.form_rule.FormRule;
import com.rcszh.lowcode.core.entity.form_rule.FormShowRule;
import com.rcszh.lowcode.core.enums.FormRuleTypeEnum;
import com.rcszh.lowcode.core.mapper.form_rule.FormShowRuleMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormRuleService {
    @Resource
    private FormShowRuleMapper formShowRuleMapper;
    public Object getAllRule() {
        return null;
    }

    /**
     * 获取指定类型的表单配置
     */
    public List<? extends FormRule> getAllRuleByType(String formId,String type) {
        if (FormRuleTypeEnum.SHOW_RULE.getType().equals(type)) {
            return formShowRuleMapper.selectList(new LambdaQueryWrapper<FormShowRule>().eq(FormShowRule::getFormId, formId));
        }
        return null;
    }

    /**
     * 添加对应类型的表单配置
     */
    public void addRule(String type, FormRule formRule) {
        if (FormRuleTypeEnum.SHOW_RULE.getType().equals(type)) {
            FormShowRule formShowRule = new FormShowRule();
            BeanUtils.copyProperties(formRule, formShowRule);
            formShowRuleMapper.insert(formShowRule);
        }
    }
}

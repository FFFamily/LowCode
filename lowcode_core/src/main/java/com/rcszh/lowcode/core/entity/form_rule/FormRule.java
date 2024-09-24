package com.rcszh.lowcode.core.entity.form_rule;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 表单规则
 * TODO 先做成多个配置，然后再合并成统一配置
 * 因为不确定其他的配置需要什么，字段方面不好定义
 */
@Data
public class FormRule {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 表单id
     */
    private String formId;
    /**
     * 表单配置类型:FormRuleTypeEnum
     */
//    private String ruleType;
}

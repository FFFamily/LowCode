package com.rcszh.lowcode.core.entity.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 表单规则
 */
public class FormRule {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 表单配置类型:FormRuleTypeEnum
     */
    private String ruleType;
}

package com.rcszh.lowcode.core.entity.form_rule;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.rcszh.lowcode.common.entity.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 表单显示配置
 */
@Getter
@Setter
public class FormShowRule extends FormRule{
    /**
     * 规则名称
     */
    private String name;
    /**
     * 前置条件
     * {
     *     formId: 表单
     *     fieldId：字段
     *     calculationSymbols: 计算符号
     *     value: 计算值
     * }
     */
    private String preConditionNode;
    /**
     * 执行操作
     * [{
     *     fieldId: 字段id
     *     fieldName：字段名称
     *     doAction：显示 or 隐藏
     * }]
     */
    private String action;
}

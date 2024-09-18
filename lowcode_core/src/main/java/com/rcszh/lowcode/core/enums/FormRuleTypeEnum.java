package com.rcszh.lowcode.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormRuleTypeEnum {
    // 校验规则
    CHECK_RULE("check"),
    // 显示规则 根据字段值，动态显示隐藏另一个字段
    SHOW_RULE("show"),
    // 填写规则
    FILL_RULE("fill")
    ;
    private final String type;
}

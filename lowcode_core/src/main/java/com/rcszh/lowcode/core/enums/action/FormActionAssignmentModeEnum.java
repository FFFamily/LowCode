package com.rcszh.lowcode.core.enums.action;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormActionAssignmentModeEnum {
    // 固定值
    FIXED_VALUE("fix_value"),
    // 公式
    FORMULA_VALUE("formula_value"),
    // 空值
    NULL_VALUE("null_value"),
    ;
    private final String mode;
}

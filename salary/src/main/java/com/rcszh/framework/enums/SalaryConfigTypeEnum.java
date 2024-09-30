package com.rcszh.framework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SalaryConfigTypeEnum {
    // 固定值
    FIXED_VALUE("fixedValue"),
    // 手动录入
    MANUAL_INPUT("manualInput"),
    // 公式计算
    FORMULA("formula"),
    ;
    private final String type;
}

package com.rcszh.lowcode.salary.enums;

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
    // 数据映射
    DATA_MAPPING("dataMapping"),
    // 系统配置
    SYSTEM_CONFIG("systemConfig");
    ;
    private final String type;
}

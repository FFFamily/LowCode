package com.rcszh.lowcode.salary.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 薪资配置公式计算类型枚举
 */
@Getter
@AllArgsConstructor
public enum SalaryConfigFormulaTypeEnum {
    // 参数
    PARAM("param"),
    // 运算符
    OPERATOR("operator"),
    ;
    private final String type;
}

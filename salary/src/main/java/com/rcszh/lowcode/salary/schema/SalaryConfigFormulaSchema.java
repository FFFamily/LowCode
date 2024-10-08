package com.rcszh.lowcode.salary.schema;

import lombok.Data;

/**
 * 配置
 */
@Data
public class SalaryConfigFormulaSchema {
    /**
     * 公式类型：参数|运算符
     */
    private String formulaType;
    /**
     * 公式值
     */
    private String formulaValue;
}

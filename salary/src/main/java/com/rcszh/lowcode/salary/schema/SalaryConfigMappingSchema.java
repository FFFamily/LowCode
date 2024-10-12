package com.rcszh.lowcode.salary.schema;

import lombok.Data;

/**
 * 数据映射解析
 */
@Data
public class SalaryConfigMappingSchema {
    /**
     * 映射目标
     */
    private String mappingTarget;
    /**
     * 映射目标属性
     */
    private String mappingProperties;
}

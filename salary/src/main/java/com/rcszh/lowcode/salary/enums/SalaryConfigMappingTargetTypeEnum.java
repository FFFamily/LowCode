package com.rcszh.lowcode.salary.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 薪资配置公式计算类型枚举
 */
@Getter
@AllArgsConstructor
public enum SalaryConfigMappingTargetTypeEnum {
    // 用户
    USER("user")
    ;
    private final String targetType;

    public static SalaryConfigMappingTargetTypeEnum getByTargetType(String targetType) {
        return Arrays.stream(SalaryConfigMappingTargetTypeEnum.values()).filter(item -> item.getTargetType().equals(targetType)).findFirst().orElse(null);
    }
}

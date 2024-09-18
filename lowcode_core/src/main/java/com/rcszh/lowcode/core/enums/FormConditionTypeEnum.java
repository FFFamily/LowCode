package com.rcszh.lowcode.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 表单动作触发条件类型枚举
 */
@Getter
@AllArgsConstructor
public enum FormConditionTypeEnum {
    // 数据触发
    DATA("data"),
    ;
    private final String type;
}

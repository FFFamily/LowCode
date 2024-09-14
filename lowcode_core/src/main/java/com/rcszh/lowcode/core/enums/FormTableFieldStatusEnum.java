package com.rcszh.lowcode.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 字段状态枚举
 */
@Getter
@AllArgsConstructor
public enum FormTableFieldStatusEnum {
    // 已创建
    CREATED("created"),
    // 已保存
    SAVED("saved"),
    // 已发布
    PUBLISHED("published"),
    // 已停用
    DEACTIVATED("deactivated"),
    ;
    private final String status;
}

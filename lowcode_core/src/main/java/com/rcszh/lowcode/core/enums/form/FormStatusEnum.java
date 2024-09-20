package com.rcszh.lowcode.core.enums.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 表单状态
 */
@Getter
@AllArgsConstructor
public enum FormStatusEnum {
    // 已创建
    CREATED("created"),
    // 已发布
    PUBLISH("publish")
    ;
    private final String status;
}

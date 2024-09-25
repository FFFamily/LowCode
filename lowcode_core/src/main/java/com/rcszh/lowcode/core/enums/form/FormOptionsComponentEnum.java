package com.rcszh.lowcode.core.enums.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 表单组件枚举
 */
@AllArgsConstructor
@Getter
public enum FormOptionsComponentEnum {
    // 输入框
    INPUT("Input"),
    // 数据源
    DATA_SOURCE_SELECT("DataSourceSelect") ,
    // 表单关联
    FORM_DATA_SELECT("FormDataSelect"),
    // 文本
    TEXT("Text")
    ;
    private final String component;
}

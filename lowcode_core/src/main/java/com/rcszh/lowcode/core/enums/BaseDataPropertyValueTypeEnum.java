package com.rcszh.lowcode.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseDataPropertyValueTypeEnum {
    INPUT("input"), // 文本输入框
    SWITCH_BUTTON("switch_button"), // 开关按钮
    ;
    private final String type;
}

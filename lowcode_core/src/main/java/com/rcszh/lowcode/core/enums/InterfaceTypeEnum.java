package com.rcszh.lowcode.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InterfaceTypeEnum {
    INPUT("input"), // 文本输入框
    NUMBER("number"), // 数值
    RADIO("radio"), // 单选框
    SWITCH_BUTTON("switch_button"), // 开关按钮
    ;
    private final String type;
}

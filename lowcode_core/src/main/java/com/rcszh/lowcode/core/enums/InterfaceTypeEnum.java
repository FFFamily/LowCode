package com.rcszh.lowcode.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InterfaceTypeEnum {
    INPUT("Input"), // 文本输入框
    NUMBER("Number"), // 数值
    RADIO("Radio"), // 单选框
    SWITCH_BUTTON("SwitchButton"), // 开关按钮
    ;
    private final String type;
}

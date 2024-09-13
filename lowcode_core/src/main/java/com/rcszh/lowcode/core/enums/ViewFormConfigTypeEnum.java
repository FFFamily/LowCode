package com.rcszh.lowcode.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ViewFormConfigTypeEnum {
    // 按钮配置
    BUTTON("button"),
    // 显示配置
    SHOW("show"),
    ;
    private final String type;
}

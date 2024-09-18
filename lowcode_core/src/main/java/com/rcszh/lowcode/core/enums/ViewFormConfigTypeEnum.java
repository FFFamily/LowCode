package com.rcszh.lowcode.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ViewFormConfigTypeEnum {
    // 按钮配置
    BUTTON("button","按钮配置"),
    // 显示配置
    SHOW("show","显示配置"),
    ;
    private final String type;
    private final String name;
}

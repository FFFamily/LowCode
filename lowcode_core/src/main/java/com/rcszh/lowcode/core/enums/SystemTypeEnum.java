package com.rcszh.lowcode.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemTypeEnum {
    // 内置
    BUILT_IN("builtin"),
    // 自定义
    CUSTOM("custom");
    ;
    private final String type;
}

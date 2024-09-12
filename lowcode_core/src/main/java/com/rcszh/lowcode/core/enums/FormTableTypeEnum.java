package com.rcszh.lowcode.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormTableTypeEnum {
    // 主表
    MAIN("main"),
    // 子表
    CHILD("child"),
    ;
    private final String type;
}

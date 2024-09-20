package com.rcszh.lowcode.core.enums.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormTypeEnum {
    // 普通表单
    NORMAL("normal")
    ;
    private final String type;
}

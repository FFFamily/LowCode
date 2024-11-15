package com.rcszh.lowcode.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ViewFormConfigTypeEnum {
    // 按钮配置
    LIST_BUTTON("List","Button","列表按钮配置"),
    LIST_SHOW("List","Show","列表显示配置"),
    DETAIL_BUTTON("Detail","Button","详情按钮配置"),
    ;
    private final String parentType;
    private final String childType;
    private final String name;
}

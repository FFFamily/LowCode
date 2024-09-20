package com.rcszh.lowcode.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ViewFormConfigTypeEnum {
    // 按钮配置
    LIST_BUTTON("list_button","列表按钮配置"),
    LIST_SHOW("list_show","列表显示配置"),
    DETAIL_BUTTON("detail_button","详情按钮配置"),
    ;
    private final String type;
    private final String name;
}

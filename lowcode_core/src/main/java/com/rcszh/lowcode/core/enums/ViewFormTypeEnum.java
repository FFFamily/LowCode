package com.rcszh.lowcode.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ViewFormTypeEnum {
    VIEW_PAGE("view_page","查看视图"),
    LIST_PAGE("list_page","列表视图"),
    ;
    private final String type;
    private final String title;
}

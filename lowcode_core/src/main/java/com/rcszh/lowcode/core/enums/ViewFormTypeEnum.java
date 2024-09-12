package com.rcszh.lowcode.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ViewFormTypeEnum {
    VIEW_PAGE("view_page"),
    LIST_PAGE("list_page"),
    ;
    private final String type;
}

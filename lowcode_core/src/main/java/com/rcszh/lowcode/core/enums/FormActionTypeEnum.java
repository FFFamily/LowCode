package com.rcszh.lowcode.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 动作类型
 */
@Getter
@AllArgsConstructor
public enum FormActionTypeEnum {
    // 新建数据
    INSERT("insert"),
    // 更新数据
    UPDATE("update"),
    // 删除数据
    DELETE("delete");
    ;
    private final String type;
}

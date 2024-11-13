package com.rcszh.lowcode.orm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SelectParamTypeEnum {
    EQ("eq"," = ")
    ;
    private final String type;
    private final String jdbcType;

    public static SelectParamTypeEnum getByType(String type) {
        for (SelectParamTypeEnum e : SelectParamTypeEnum.values()) {
            if (e.type.equals(type)) {
                return e;
            }
        }
        throw new RuntimeException("错误的SQL查询条件类型");
    }
}

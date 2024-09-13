package com.rcszh.lowcode.orm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SqlFieldTypeEnum {
    STRING("String","varchar(255)")
    ;
    private final String fieldType;
    private final String sqlType;

    public static SqlFieldTypeEnum getSqlFieldTypeEnum(String fieldType) {
        for (SqlFieldTypeEnum sqlFieldTypeEnum : SqlFieldTypeEnum.values()) {
            if (sqlFieldTypeEnum.fieldType.equals(fieldType)) {
                return sqlFieldTypeEnum;
            }
        }
    }
}

package com.rcszh.lowcode.orm;

import lombok.Getter;
import lombok.Setter;

/**
 * sql 字段
 */
@Getter
@Setter
public class SqlFieldConfig {
    private String fieldName;
    private String fieldType;
    private String fieldComment;
    private String fieldDefault;
    private String fieldLength;
    private String fieldPrecision;
    private String fieldScale;
    private Boolean isNull;
    private Boolean isIndex;
}

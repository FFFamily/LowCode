package com.rcszh.lowcode.orm.entity;

import lombok.Data;

@Data
public class FilterCondition {
    private String field;
    private String operator;
    private String value;
}

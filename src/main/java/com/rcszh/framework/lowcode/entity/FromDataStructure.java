package com.rcszh.framework.lowcode.entity;

import lombok.Getter;
import lombok.Setter;

// 表单数据
@Getter
@Setter
public class FromDataStructure extends BaseEntity{
    // 表单名称
    private String name;
    // 表单编码
    private String code;
    // TODO 表名
    private String tableName;
}

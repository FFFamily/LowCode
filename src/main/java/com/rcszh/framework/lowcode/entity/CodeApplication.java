package com.rcszh.framework.lowcode.entity;

import lombok.Data;

/**
 * 低代码应用
 */
public class CodeApplication  extends BaseEntity{
    // 编码
    private String code;
    // 名称
    private String name;
    // 分组id
    private String applicationGroupId;
}

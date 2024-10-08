package com.rcszh.lowcode.salary.entity;

import lombok.Data;

/**
 * 薪资配置
 */
@Data
public class SalaryConfig {
    private String id;
    /**
     * 配置名称
     */
    private String name;
    /**
     * 录入类型
     */
    private String type;
    /**
     * 配置值
     */
    private String value;
    /**
     * 模块id
     */
    private String groupId;
    /**
     * 排序
     */
    private String configOrder;
}

package com.rcszh.framework.lowcode.entity.base;

/**
 * 基础数据属性配置信息
 */
public class BaseDataProperty {
    // 配置标题
    private String key;
    /**
     * 配置值类型
     * {@link com.rcszh.framework.lowcode.enums.BaseDataPropertyValueTypeEnum} 对应枚举
     */
    private Integer valueType;
    /**
     * 配置具体值（JSON形式）
     */
    private String value;
}

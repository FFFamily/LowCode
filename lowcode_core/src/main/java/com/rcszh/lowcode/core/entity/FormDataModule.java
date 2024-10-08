package com.rcszh.lowcode.core.entity;

import com.rcszh.lowcode.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据组件
 */
@Getter
@Setter
public class FormDataModule extends BaseEntity {
    // 组件名称
    private String name;
    // 组件类型
    private Integer type;
    // 字段类型
    private String dataType;
}

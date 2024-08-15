package com.rcszh.framework.lowcode.entity;

import com.rcszh.framework.lowcode.entity.base.BaseEntity;
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

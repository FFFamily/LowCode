package com.rcszh.framework.lowcode.entity;

import com.rcszh.framework.lowcode.entity.base.BaseEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据字段
 */
@Getter
@Setter
public class FormDataField extends BaseEntity {
    // 数据表单id
    @NotNull
    private String FromDataTableId;
    // 字段名
    @NotNull
    private String name;
    // 显示名
    private String showName;
    // 字段编码
    @NotNull
    private String code;
    // 组件id
    private Integer moduleId;
    // 状态
    private Integer status;
    // 描述
    private String description;
    // 数据属性
    private Object dataProperty;
    // 样式属性
    private Object styleProperty;
}

package com.rcszh.lowcode.core.entity;

import com.rcszh.lowcode.common.entity.BaseEntity;
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
    // 组件类型
    private Integer moduleType;
    // 数据类型（由组件类型决定）
    private Integer dataType;
    // 状态
    private Integer status;
    // 描述
    private String description;
    // 数据属性(JSON)
    private String dataProperty;
    // 样式属性(JSON)
    private String styleProperty;
}

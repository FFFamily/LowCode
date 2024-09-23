package com.rcszh.lowcode.core.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FieldCreateRequest  {
    /**
     * 组件默认值
     */
    private String defaultValue;
    /**
     * 组件是否只读
     */
    private Boolean readOnly;
    /**
     * 组件枚举值
     */
    private List<Object> enumValues;
}

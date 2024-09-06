package com.rcszh.lowcode.core.requests;

import com.rcszh.lowcode.core.entity.data_source.CoreDataSourceField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldCreateRequest extends CoreDataSourceField {
    /**
     * 字段默认值
     */
    private String defaultValue;
    /**
     * 是否只读
     */
    private Boolean readOnly;
}

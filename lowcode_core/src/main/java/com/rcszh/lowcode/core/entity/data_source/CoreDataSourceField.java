package com.rcszh.lowcode.core.entity.data_source;

import com.rcszh.lowcode.common.entity.BaseTenantEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoreDataSourceField extends BaseTenantEntity {
    /**
     * 字段组件id
     */
    private String id;
    /**
     * 字段组件类型
     */
    private Integer type;
    /**
     * 字段组件名称
     */
    private String name;
    /**
     * 字段标识
     */
    private String fieldName;
    /**
     * 字段类型
     */
    private String fieldType;
    /**
     * 字段描述
     */
    private String fieldComment;
    /**
     * 字段默认值
     */
    private String fieldDefault;
    /**
     * 字段长度
     */
    private String fieldLength;
    /**
     * 字段索引
     */
    private String fieldIndex;
}

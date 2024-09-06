package com.rcszh.lowcode.core.entity.data_source;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rcszh.lowcode.common.entity.BaseTenantEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("core_data_source_field")
public class CoreDataSourceField extends BaseTenantEntity {
    /**
     * 字段组件id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 表Id
     */
    private Long tableId;
    /**
     * 字段组件类型
     * type: input、text...
     */
    private String type;

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
     * schema type string、object、array...
     */
    private String fieldType;
    /**
     * 字段配置
     */
    private String options;
    /**
     * 字段描述
     */
    private String fieldComment;

    /**
     * 字段长度
     */
    private String fieldLength;
    /**
     * 字段索引
     */
    private String fieldIndex;

    //以下和UI字段渲染相关
}

package com.rcszh.lowcode.core.entity.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 组件
 */
public class FormTableField {
    /**
     * 字段组件id
     */
    @TableId(type = IdType.AUTO)
    private String id;
    /**
     * 表Id
     */
    private Long formTableId;
    /**
     * 字段编码（对应真实数据库字段）
     */
    private String key;
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
     * 字段配置（UI）
     */
    private String options;
    /**
     * 字段索引
     */
    private String fieldIndex;
    /**
     * 组件描述
     */
    private String description;
}

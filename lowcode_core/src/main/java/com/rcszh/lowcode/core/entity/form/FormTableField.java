package com.rcszh.lowcode.core.entity.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 组件
 */
@Getter
@Setter
@TableName("form_table_field")
public class FormTableField {
    /**
     * 字段组件id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 表单Id
     */
    private String formId;
    /**
     * 表Id
     */
    private String formTableId;
    /**
     * 字段编码（对应真实数据库字段）
     * 原（code）
     */
    private String name;
    /**
     * 字段组件类型
     * type: input、text...
     */
    private String component;
    /**
     * 对应数据库类型
     * 原 type
     */
    private String jdbcType;
    /**
     * 字段组件名称
     * 原 name
     */
    private String label;
    /**
     * 状态
     */
    private String status;
    /**
     * 前端UI prop
     */
    private String props;
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

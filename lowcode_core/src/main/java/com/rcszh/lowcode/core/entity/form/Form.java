package com.rcszh.lowcode.core.entity.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 表单
 */
public class Form {
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 表单名称
     */
    private String name;
    /**
     * 真实的数据库表名（表单编码）
     */
    private String tableName;
    /**
     * 数据库类型: 普通表、SQL表
     */
    private String type;
}

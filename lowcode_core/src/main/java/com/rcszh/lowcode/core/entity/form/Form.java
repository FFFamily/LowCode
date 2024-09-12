package com.rcszh.lowcode.core.entity.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 表单
 */
@Getter
@Setter
@TableName("form")
public class Form {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 表单名称
     */
    private String name;
    /**
     * 表单编码
     */
    private String code;
    /**
     * 数据库类型: 普通表
     */
    private String type;
    /**
     * 启用的视图
     */
    private String viewFormId;
    /**
     * 启用的视图
     */
    private String viewListFormId;
}

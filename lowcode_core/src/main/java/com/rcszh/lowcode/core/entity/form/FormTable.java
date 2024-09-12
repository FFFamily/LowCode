package com.rcszh.lowcode.core.entity.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rcszh.lowcode.common.entity.BaseTenantEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 与表单关联的数据库信息
 */
@Getter
@Setter
@TableName("form_table")
public class FormTable  {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 关联的表单Id
     */
    private String formId;
    /**
     * 数据库表名
     */
    private String name;
    /**
     * 真实的数据库表名（表单编码）
     */
    private String tableName;
    /**
     * 数据库类型: 主表、子表
     */
    private String type;

}

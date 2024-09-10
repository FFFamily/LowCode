package com.rcszh.lowcode.core.entity.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rcszh.lowcode.common.entity.BaseTenantEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("core_data_source_table")
public class FormTable extends BaseTenantEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
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
     * 数据库类型: 主表、从表
     */
    private String type;

}

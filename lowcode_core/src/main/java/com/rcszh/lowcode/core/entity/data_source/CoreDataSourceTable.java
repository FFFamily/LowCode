package com.rcszh.lowcode.core.entity.data_source;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rcszh.lowcode.common.entity.BaseTenantEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("core_data_source_table")
public class CoreDataSourceTable extends BaseTenantEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 数据源Id
     */
    private Long codeDataSourceId;
    /**
     * 数据库名称
     */
    private String name;
    /**
     * 数据库类型: 普通表、SQL表
     */
    private String type;
    /**
     * 真实的数据库表名
     */
    private String tableName;
}

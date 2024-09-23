package com.rcszh.lowcode.core.entity.data_source;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class CoreDataSourceConfig {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 配置名称
     */
    private String name;
    /**
     * sql 查询
     */
    private String sqlStatement;
}

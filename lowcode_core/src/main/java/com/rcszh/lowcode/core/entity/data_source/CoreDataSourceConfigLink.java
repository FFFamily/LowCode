package com.rcszh.lowcode.core.entity.data_source;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class CoreDataSourceConfigLink {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String dataSourceId;
    private String dataSourceConfigId;
}

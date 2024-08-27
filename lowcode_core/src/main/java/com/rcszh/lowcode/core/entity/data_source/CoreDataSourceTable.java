package com.rcszh.lowcode.core.entity.data_source;

import com.rcszh.lowcode.common.entity.BaseTenantEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoreDataSourceTable extends BaseTenantEntity {
    private String name;
    private Integer type;
    private String tableName;
}

package com.rcszh.lowcode.core.entity.data_source;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rcszh.lowcode.common.entity.BaseTenantEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("data_source")
public class CoreDataSource extends BaseTenantEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String url;
    private String password;
    private String username;
    private Integer status;
}

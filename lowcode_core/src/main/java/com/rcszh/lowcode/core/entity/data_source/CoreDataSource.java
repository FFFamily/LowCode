package com.rcszh.lowcode.core.entity.data_source;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rcszh.lowcode.common.entity.BaseTenantEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("core_data_source")
public class CoreDataSource extends BaseTenantEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 数据源名称
     */
    private String dsName;
    /**
     * 数据表名称
     */
    private String name;
    /**
     * 数据源类型
     */
    private String type;
    /**
     * 数据源地址
     */
    private String url;
    /**
     * 数据源账号密码
     */
    private String password;
    /**
     * 数据源用户名
     */
    private String username;
}

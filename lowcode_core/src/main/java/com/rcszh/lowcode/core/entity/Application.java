package com.rcszh.lowcode.core.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rcszh.lowcode.common.entity.BaseEntity;
import com.rcszh.lowcode.common.entity.BaseTenantEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 低代码应用
 */
@Getter
@Setter
@TableName("application")
public class Application extends BaseTenantEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    // 编码
    private String code;
    // 名称
    private String name;
    // 分类
    private Integer type;
    // 应用状态
    private Integer status;
}

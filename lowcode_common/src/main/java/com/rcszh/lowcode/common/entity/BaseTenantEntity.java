package com.rcszh.lowcode.common.entity;


import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 租户实体 Entity基类
 */
@Getter
@Setter
public class BaseTenantEntity extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 租户id
     */
    private Long tenantId;
}

package com.rcszh.lowcode.account.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTenantBody {
    /**
     * 租户状态
     */
    private String status;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 管理员用户名
     */
    private String userName;

    /**
     * 租户备注
     */
    private String remark;
}

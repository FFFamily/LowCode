package com.rcszh.lowcode.account.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户和角色关联 sys_user_role
 */
@Getter
@Setter
public class SysUserRole {
    /** 用户ID */
    private Long userId;
    /** 角色ID */
    private Long roleId;
}

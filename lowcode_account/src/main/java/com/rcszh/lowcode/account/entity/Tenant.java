package com.rcszh.lowcode.account.entity;

import com.rcszh.lowcode.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 租户信息 Account
 */
@Getter
@Setter
public class Tenant extends BaseEntity {
    /** 租户编号 */
    private String code;

    /** NEW租户编号 */
    private String newTenantCode;

    /** 父级租户编号 */
    private String parentCode;

    /** 租户名称 */
    private String name;

    /** 用户昵称 */
    private String nickName;

    /** 租户邮箱 */
    private String email;

    /** 手机号码 */
    private String phoneNumber;

    /** 联系地址 */
    private String address;

    /** 租户状态（0正常 1停用） */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 角色组 */
    private Long[] roleIds;

    /** 使用期限 */
    private String tenantTerm;

    /** 激活码 */
    private String tenantKey;
}

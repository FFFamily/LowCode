package com.rcszh.lowcode.account.entity;

import com.rcszh.lowcode.common.entity.BaseEntity;

import java.util.List;

/**
 * 用户对象
 */
public class User extends BaseEntity {
    /**
     * 租户编号
     */
    private String tenantCode;
    /**
     * 所属租户
     */
    private Tenant tenant;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 部门对象
     */
    private Dept dept;
    /**
     * 岗位组
     */
    private Long[] postIds;
    /**
     * 部门父ID
     */
    private Long parentId;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 角色列表
     */
    private List<Role> roles;
    /**
     * 角色组
     */
    private Long[] roleIds;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户类型
     */
    private String userType;
    /**
     * 手机号码
     */
    private String phoneNumber;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐加密
     */
    private String salt;
    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;
}

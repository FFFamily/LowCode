package com.rcszh.lowcode.account.model;

import com.rcszh.lowcode.account.entity.core.SysDept;
import com.rcszh.lowcode.account.entity.core.SysRole;
import com.rcszh.lowcode.account.entity.core.SysUser;

import java.util.List;

public class User extends SysUser {
    /**
     * 部门对象
     */
    private SysDept dept;
    /**
     * 角色对象
     */
    private List<SysRole> roles;
    /**
     * 角色组
     */
    private Long[] roleIds;
    /**
     * 岗位组
     */
    private Long[] postIds;
    /**
     * 角色ID
     */
    private Long roleId;
}

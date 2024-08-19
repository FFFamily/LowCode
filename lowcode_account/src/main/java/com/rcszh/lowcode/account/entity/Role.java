package com.rcszh.lowcode.account.entity;


import com.rcszh.lowcode.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 角色
 */
@Getter
@Setter
public class Role extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /** 角色ID */
    private Long id;

    /** 租户编号 */
    private String AccountCode;

    /** 角色名称 */
    private String name;

    /** 角色权限 */
    private String key;

    /** 角色排序 */
    private String sort;

    /** 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限） */
    private String dataScope;

    /** 角色状态（0正常 1停用） */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 用户是否存在此角色标识 默认不存在 */
    private boolean flag = false;

    /** 菜单组 */
    private Long[] menuIds;

    /** 部门组（数据权限） */
    private Long[] deptIds;

}

package com.rcszh.lowcode.account.entity;

import com.rcszh.lowcode.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 部门
 */
@Getter
@Setter
public class Dept extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /** 租户编号 */
    private String tenantCode;

    /** 父部门ID */
    private Long parentId;
    /** 父部门名称 */
    private String parentName;
    /** 祖级列表 */
    private String ancestors;
    /** 部门名称 */
    private String name;

    /** 显示顺序 */
    private String orderNum;

    /** 负责人 */
    private String leader;

    /** 联系电话 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 部门状态:0正常,1停用 */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

}


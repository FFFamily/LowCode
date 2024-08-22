package com.rcszh.lowcode.account.entity.core;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.rcszh.lowcode.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 租户对象 sys_tenant
 */
@Getter
@Setter
public class SysTenant extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 租户id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 显示顺序
     */
    private Integer orderNum;
    /**
     * 租户状态（0正常 1停用）
     */
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;
    /**
     * 关联用户
     */
    private SysUser sysUser;
    /**
     * 关联机构
     */
    private SysDept sysDept;
    /**
     * 备注
     */
    private String remark;
}

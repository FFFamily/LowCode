package com.rcszh.lowcode.account.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.rcszh.lowcode.common.entity.BaseTenantEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 岗位表 sys_post
 */
@Getter
@Setter
public class SysPost extends BaseTenantEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /** 岗位序号 */
    @TableId(type = IdType.ASSIGN_ID)
    private Long postId;

    /** 岗位编码 */
   
    private String postCode;

    /** 岗位名称 */
   
    private String postName;

    /** 岗位排序 */
   
    private Integer postSort;

    /** 状态（0正常 1停用） */
   
    private String status;

    /** 用户是否存在此岗位标识 默认不存在 */
    private boolean flag = false;
}

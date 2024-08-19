package com.rcszh.lowcode.account.entity;


import com.rcszh.lowcode.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 岗位表 sys_post
 */
@Setter
@Getter
public class Post extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /** 岗位序号 */
    private Long postId;

    /** 租户编号 */
    private String tenantCode;

    /** 岗位编码 */
    private String postCode;

    /** 岗位名称 */
    private String postName;

    /** 岗位排序 */
    private String postSort;

    /** 状态（0正常 1停用） */
    private String status;

    /** 用户是否存在此岗位标识 默认不存在 */
    private boolean flag = false;

}

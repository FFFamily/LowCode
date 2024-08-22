package com.rcszh.lowcode.account.entity.core;


import com.rcszh.lowcode.common.entity.BaseEntity;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 字典类型表 sys_dict_type
 */
@Getter
@Setter
public class SysDictType extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /** 字典主键 */
    
    private Long dictId;

    /** 字典名称 */
    
    private String dictName;

    /** 字典类型 */
    
    private String dictType;

    /** 状态（0正常 1停用） */
    
    private String status;
}

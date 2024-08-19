package com.rcszh.lowcode.core.entity;

import com.rcszh.framework.lowcode.entity.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 表单数据详情
 */
@Getter
@Setter
public class FromData extends BaseEntity {
    private String code;
    private String type;
}

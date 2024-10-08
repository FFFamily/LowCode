package com.rcszh.lowcode.core.entity;


import com.rcszh.lowcode.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 低代码应用
 */
@Getter
@Setter
public class Application extends BaseEntity {
    // 编码
    private String code;
    // 名称
    private String name;
    // 分类
    private Integer type;
    // 应用状态
    private Integer status;
    // 分组id
    private String applicationGroupId;
}

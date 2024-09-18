package com.rcszh.lowcode.core.entity.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewForm {
    private String id;
    /**
     * 表单id
     */
    private String formId;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型：详情|列表
     */
    private String type;
    /**
     * 系统类型:SystemTypeEnum
     */
    private String systemType;
}

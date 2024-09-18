package com.rcszh.lowcode.core.entity.action;

import lombok.Data;

/**
 * 表单动作
 */
@Data
public class FormAction {
    private String id;
    /**
     * 名称
     */
    private String acName;
    /**
     * 动作类型
     */
    private String acType;
    /**
     * 动作源
     */
    private String acResource;
    /**
     * 动作目标（目标表单Id）
     */
    private String acTarget;
    /**
     * 动作配置
     */
    private String acConfig;
}

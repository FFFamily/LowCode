package com.rcszh.lowcode.core.entity.action;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 表单触发条件（场景）
 */
@Data
public class FormCondition {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 表单id
     */
    private String formId;
    /**
     * 条件名称
     */
    private String cdName;
    /**
     * 触发条件类型
     */
    private String cdType;
    /**
     * 触发条件配置
     * {
     *     trEvent: 触发事件
     *     trTarget：触发目标
     *     trCondition: 触发条件：{
     *         field: 目标字段
     *         operator：条件
     *         valueType: 值类型
     *         value: 值
     *     }
     * }
     */
    private String cdConfig;
    /**
     * 是否启用
     */
    private String cdEnable;
}

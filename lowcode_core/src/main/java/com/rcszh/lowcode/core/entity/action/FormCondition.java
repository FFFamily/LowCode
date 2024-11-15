package com.rcszh.lowcode.core.entity.action;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 表单触发条件（场景）
 */
@Data
@TableName("form_condition")
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
     * 监控对象
     */
    private String cdResource;
    /**
     * 是否启用
     */
    private String cdEnable;

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
//    private String cdConfig;

}

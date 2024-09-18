package com.rcszh.lowcode.core.entity.action;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 表单动作场景中间关联表
 */
@Data
public class FormActionCondition {
    private String id;
    private String actionId;
    private String conditionId;
}

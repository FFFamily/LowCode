package com.rcszh.lowcode.core.dto.action;

import com.rcszh.lowcode.core.entity.action.FormAction;
import com.rcszh.lowcode.core.entity.action.FormCondition;
import lombok.Data;

import java.util.List;

/**
 * 创建动作
 */
@Data
public class FormActionCreateDto {
    // 动作场景
    private FormCondition formCondition;
    // 需要被执行的动作
    private List<FormAction> formActions;
}

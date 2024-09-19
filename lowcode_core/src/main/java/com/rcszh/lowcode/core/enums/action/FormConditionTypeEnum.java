package com.rcszh.lowcode.core.enums.action;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 表单动作触发条件类型枚举
 */
@Getter
@AllArgsConstructor
public enum FormConditionTypeEnum {
    // 数据触发
    DATA("data",new String[]{FormActionTypeEnum.INSERT.getType(),FormActionTypeEnum.UPDATE.getType(),FormActionTypeEnum.DELETE.getType()}),
    ;
    private final String type;
    private final String[] actions;

    /**
     * 将动作类型枚举转换成动作条件类型枚举
     */
    public static FormConditionTypeEnum covert(String trEvent) {
        for (FormConditionTypeEnum value : FormConditionTypeEnum.values()) {
            String[] actionsList = value.getActions();
            for (String action : actionsList) {
                if (action.equals(trEvent)) {
                    return value;
                }
            }
        }
        return null;
    }
}

package com.rcszh.lowcode.core.entity.action;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 表单动作
 */
@Data
public class FormAction {
    @TableId(type = IdType.ASSIGN_ID)
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
//    private String acResource;
    /**
     * 动作目标（目标表单Id）
     */
    private String acTargetTableId;
    /**
     * 动作配置
     * {"dataList": [{"fieldName": "id", "fieldValue": "11", "assignmentMode": "fix_value"}]}
     */
    private String acConfig;
}

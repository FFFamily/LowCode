package com.rcszh.lowcode.core.entity.view;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ViewForm {
    @TableId(type = IdType.ASSIGN_ID)
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
     * 状态
     */
    private String status;
    /**
     * 类型：详情|列表
     */
    private String type;
    /**
     * 系统类型:SystemTypeEnum
     */
    private String systemType;
}

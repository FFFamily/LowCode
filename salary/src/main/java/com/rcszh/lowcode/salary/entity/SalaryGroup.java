package com.rcszh.lowcode.salary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 薪资配置
 */
@Data
public class SalaryGroup {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 配置名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer groupOrder;
}

package com.rcszh.lowcode.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
//    /**
//     * 创建者
//     */
//    @TableField(fill = FieldFill.INSERT)
//    private String createBy;
//    /**
//     * 创建时间
//     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @TableField(fill = FieldFill.INSERT)
//    private Date createTime;
//    /**
//     * 更新者
//     */
//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    private String updateBy;
//    /**
//     * 更新时间
//     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    private Date updateTime;
//    /**
//     * 备注
//     */
//    private String remark;
}

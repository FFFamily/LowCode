package com.rcszh.framework.lowcode.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class BaseEntity {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
}

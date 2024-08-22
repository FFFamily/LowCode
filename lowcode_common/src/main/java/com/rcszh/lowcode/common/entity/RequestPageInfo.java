package com.rcszh.lowcode.common.entity;

import lombok.Data;

/**
 * 请求登录信息
 */
@Data
public class RequestPageInfo {
    private Integer pageNum;
    private Integer pageSize;
}

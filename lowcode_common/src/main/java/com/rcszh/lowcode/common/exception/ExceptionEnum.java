package com.rcszh.lowcode.common.exception;

import lombok.Getter;

/**
 * 异常处理枚举类
 */
@Getter
public enum ExceptionEnum {
    // 数据操作错误定义
    SUCCESS(2000L, "请求成功"),
    BODY_NOT_MATCH(4000L,"请求的数据格式不符"),
    SIGNATURE_NOT_MATCH(4001L,"请求的数字签名不匹配"),
    NOT_FOUND(4004L, "未找到该资源"),
    INTERNAL_SERVER_ERROR(5000L, "服务器内部错误："),
    SERVER_BUSY(5003L,"服务器正忙，请稍后再试");
    /**
     * 错误码
     */
    private final Long resultCode;
    /**
     * 错误描述
     */
    private final String resultMsg;

    ExceptionEnum(Long resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }


}


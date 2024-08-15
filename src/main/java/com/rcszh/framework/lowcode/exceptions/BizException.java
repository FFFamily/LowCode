package com.rcszh.framework.lowcode.exceptions;


import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 服务错误
 * @description: 自定义异常类
 */
@Setter
@Getter
public class BizException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public BizException() {
        super();
    }


    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}


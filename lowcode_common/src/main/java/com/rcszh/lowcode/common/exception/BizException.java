package com.rcszh.lowcode.common.exception;


import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 服务错误
 * 自定义异常类
 */
@Setter
@Getter
public class BizException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected Long errorCode;
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

    public BizException(Long errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(Long errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}


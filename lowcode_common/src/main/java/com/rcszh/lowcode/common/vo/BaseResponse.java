package com.rcszh.lowcode.common.vo;

import com.rcszh.lowcode.common.exception.ExceptionEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BaseResponse {
    /**
     * 响应代码
     */
    private Long code;

    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应结果
     */
    private Object data;

    /**
     * 成功
     */
    public static BaseResponse success() {
        return success(null);
    }

    /**
     * 成功
     */
    public static BaseResponse success(Object data) {
        BaseResponse rb = new BaseResponse();
        rb.setCode(ExceptionEnum.SUCCESS.getResultCode());
        rb.setMessage(ExceptionEnum.SUCCESS.getResultMsg());
        rb.setData(data);
        return rb;
    }

    /**
     * 失败
     */
    public static BaseResponse error(ExceptionEnum errorInfo) {
        BaseResponse rb = new BaseResponse();
        rb.setCode(errorInfo.getResultCode());
        rb.setMessage(errorInfo.getResultMsg());
        rb.setData(null);
        return rb;
    }

    /**
     * 失败
     */
    public static BaseResponse error(Long code, String message) {
        BaseResponse rb = new BaseResponse();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setData(null);
        return rb;
    }

    /**
     * 失败
     */
    public static BaseResponse error( String message) {
        BaseResponse rb = new BaseResponse();
        rb.setCode(ExceptionEnum.INTERNAL_SERVER_ERROR.getResultCode());
        rb.setMessage(message);
        rb.setData(null);
        return rb;
    }

}

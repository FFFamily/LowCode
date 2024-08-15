package com.rcszh.framework.lowcode.vo;

import com.rcszh.framework.lowcode.exceptions.ExceptionEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseResponse {
    /**
     * 响应代码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private Object result;

    public BaseResponse() {
    }


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
        rb.setResult(data);
        return rb;
    }

    /**
     * 失败
     */
    public static BaseResponse error(ExceptionEnum errorInfo) {
        BaseResponse rb = new BaseResponse();
        rb.setCode(errorInfo.getResultCode());
        rb.setMessage(errorInfo.getResultMsg());
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static BaseResponse error(String code, String message) {
        BaseResponse rb = new BaseResponse();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static BaseResponse error( String message) {
        BaseResponse rb = new BaseResponse();
        rb.setCode("-1");
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

}

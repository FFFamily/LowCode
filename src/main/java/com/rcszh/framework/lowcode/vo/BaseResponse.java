package com.rcszh.framework.lowcode.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<T>   {
    private String msg;
    private Integer code;
    private T data;
    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(200);
        return response;
    }
}

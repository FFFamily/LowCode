package com.rcszh.framework.lowcode.exceptions;

/**
 * 服务错误
 */
public class ServerException extends RuntimeException {
    public ServerException(String message) {
        super("后台服务错误：" + message);
    }
}

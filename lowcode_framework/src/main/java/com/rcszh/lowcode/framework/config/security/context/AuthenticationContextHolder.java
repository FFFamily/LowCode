package com.rcszh.lowcode.framework.config.security.context;

import org.springframework.security.core.Authentication;

/**
 * 当前用户认证信息存放
 */
public class AuthenticationContextHolder {
    private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<>();
    /**
     * 获取当前线程用户的认证信息
     */
    public static Authentication getContext() {
        return contextHolder.get();
    }
    /**
     * 设置当前线程用户的认证信息
     */
    public static void setContext(Authentication context) {
        contextHolder.set(context);
    }
    /**
     * 清除当前线程用户的认证信息
     */
    public static void clearContext() {
        contextHolder.remove();
    }
}

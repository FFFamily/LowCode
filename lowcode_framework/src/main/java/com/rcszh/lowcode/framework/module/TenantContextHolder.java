package com.rcszh.lowcode.framework.module;

public class TenantContextHolder {
    /**
     * 获取当前登录的租户信息
     */
    public static String getCurrentTenantCode(){
        return "admin";
    }
}

package com.rcszh.lowcode.common.utils;

public class RoleUtil {
    public static boolean idAdmin(Long roleId){
        return roleId != null && 1L == roleId;
    }
}

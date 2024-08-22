package com.rcszh.lowcode.account.utils;

import com.rcszh.lowcode.account.entity.core.SysUser;
import com.rcszh.lowcode.common.entity.LoginUser;
import com.rcszh.lowcode.common.enums.UserType;

/**
 * 用户工具类
 */
public class UserUtil {
    /**
     * 判断当前用户是否为管理员
     */
    public static boolean isAdmin(LoginUser loginUser) {
        return isAdmin(loginUser.getUserId(),loginUser.getUserType());
    }
    /**
     * 判断当前用户是否为管理员
     */
    public static boolean isAdmin(Long userId,String userType){
        return userId!=null&& UserType.SuperAdmin.getCode().equals(userType);
    }
    /**
     * 判断当前用户是否为管理员
     */
    public static boolean isAdmin(SysUser sysUser){
        return isAdmin(sysUser.getId(),sysUser.getType());
    }
}

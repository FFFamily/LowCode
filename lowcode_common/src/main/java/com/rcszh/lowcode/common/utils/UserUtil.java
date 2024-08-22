package com.rcszh.lowcode.common.utils;

import com.rcszh.lowcode.common.entity.LoginUser;
import com.rcszh.lowcode.common.enums.UserType;

/**
 * 用户工具类
 */
public class UserUtil {
    /**
     * 判断当前用户是否为管理员
     */
    public static boolean idAdmin(LoginUser loginUser) {
        return idAdmin(loginUser.getUserId(),loginUser.getUserType());
    }
    /**
     * 判断当前用户是否为管理员
     */
    public static boolean idAdmin(Long userId,String userType){
        return userId!=null&& UserType.SuperAdmin.getCode().equals(userType);
    }
}

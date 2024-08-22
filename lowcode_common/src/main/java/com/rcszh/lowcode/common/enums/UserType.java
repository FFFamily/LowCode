package com.rcszh.lowcode.common.enums;

import lombok.Getter;

@Getter
public enum UserType {
    None("0", "其他"),
    User("1", "普通账号"),
    Admin("4", "系统管理员"),
    SuperAdmin("999", "超级管理员");

    private final String code;
    private final String info;

    UserType(String code, String info) {
        this.code = code;
        this.info = info;
    }

}

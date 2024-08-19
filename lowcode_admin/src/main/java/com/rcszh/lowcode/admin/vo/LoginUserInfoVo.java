package com.rcszh.lowcode.admin.vo;

import com.rcszh.lowcode.account.entity.User;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
@Builder
@Data
public class LoginUserInfoVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private User userInfo;
    /**
     * 拥有的权限
     */
    private List<String> permissions;
}

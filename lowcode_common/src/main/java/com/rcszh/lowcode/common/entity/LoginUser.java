package com.rcszh.lowcode.common.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * TODO 要求可扩展
 */
@Getter
@Setter
public class LoginUser implements UserDetails {
    private String password;
    private String username;
    private Long userId;
    private Long deptId;
    private String userType;
    private Long tenantId;
    private String token;
    private Long expireTime;
    private Long loginTime;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

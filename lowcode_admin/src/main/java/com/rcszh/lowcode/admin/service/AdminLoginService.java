package com.rcszh.lowcode.admin.service;

import com.rcszh.lowcode.common.entity.LoginUser;
import com.rcszh.lowcode.common.exception.BizException;
import com.rcszh.lowcode.framework.config.security.context.AuthenticationContextHolder;
import com.rcszh.lowcode.framework.service.TokenService;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * 登录服务
 */
public class AdminLoginService {
    /**
     * 处理身份验证请求
     */
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private TokenService tokenService;
    /**
     * 租户用户登录
     */
    public String login(String userName, String password) {
        Authentication authentication;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception var10) {
            if (var10 instanceof BadCredentialsException) {
                throw new BizException("用户名或账号密码错误");
            }
            throw new BizException(var10.getMessage());
        } finally {
            AuthenticationContextHolder.clearContext();
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return tokenService.createToken(loginUser);
    }
}

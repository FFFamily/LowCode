package com.rcszh.lowcode.admin.service;

import com.rcszh.lowcode.account.entity.core.SysUser;
import com.rcszh.lowcode.account.service.ISysUserService;
import com.rcszh.lowcode.common.enums.UserStatus;
import com.rcszh.lowcode.common.exception.ServiceException;
import com.rcszh.lowcode.common.utils.SecurityUtils;
import com.rcszh.lowcode.framework.config.security.context.AuthenticationContextHolder;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * 用户认证逻辑
 */
@Service
public class AdminUserDetailsService implements UserDetailsService {
    @Resource
    private ISysUserService userService;
//    @Value(value = "${user.password.maxRetryCount}")
//    private int maxRetryCount;
//
//    @Value(value = "${user.password.lockTime}")
//    private int lockTime;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.selectUserByUserName(username);
        if (sysUser == null) {
            throw new ServiceException("登录用户：" + username + " 不存在");
        } else if (UserStatus.DISABLE.getCode().equals(sysUser.getStatus())) {
            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
        }
        validate(sysUser);
        return null;
    }

    public void validate(SysUser user) {
        Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();
        String username = usernamePasswordAuthenticationToken.getName();
        String password = usernamePasswordAuthenticationToken.getCredentials().toString();
//
//        Integer retryCount = redisCache.getCacheObject(getCacheKey(username));
//
//        if (retryCount == null)
//        {
//            retryCount = 0;
//        }
//        if (retryCount >= Integer.valueOf(maxRetryCount).intValue()) {
//            throw new UserPasswordRetryLimitExceedException(maxRetryCount, lockTime);
//        }
        if (!matches(user, password)) {
//            retryCount = retryCount + 1;
//            redisCache.setCacheObject(getCacheKey(username), retryCount, lockTime, TimeUnit.MINUTES);
//            throw new UserPasswordNotMatchException();
            throw new RuntimeException();
        } else {
            clearLoginRecordCache(username);
        }
    }

    public boolean matches(SysUser user, String rawPassword) {
        return SecurityUtils.matchesPassword(rawPassword, user.getPassword());
    }

    public void clearLoginRecordCache(String loginName) {
//        if (redisCache.hasKey(getCacheKey(loginName))) {
//            redisCache.deleteObject(getCacheKey(loginName));
//        }
    }
}

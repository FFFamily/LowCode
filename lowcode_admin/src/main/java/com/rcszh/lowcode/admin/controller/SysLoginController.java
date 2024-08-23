package com.rcszh.lowcode.admin.controller;


import com.rcszh.lowcode.account.entity.core.SysMenu;
import com.rcszh.lowcode.account.entity.core.SysUser;
import com.rcszh.lowcode.account.service.ISysMenuService;
import com.rcszh.lowcode.account.service.module.SysPermissionService;
import com.rcszh.lowcode.admin.dto.LoginRequest;
import com.rcszh.lowcode.common.constant.UserConstants;
import com.rcszh.lowcode.common.entity.LoginUser;
import com.rcszh.lowcode.common.exception.ServiceException;
import com.rcszh.lowcode.common.utils.SecurityUtils;
import com.rcszh.lowcode.common.utils.StringUtils;
import com.rcszh.lowcode.common.vo.BaseResponse;
import com.rcszh.lowcode.framework.config.security.context.AuthenticationContextHolder;
import com.rcszh.lowcode.framework.service.TokenService;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.exception.user.*;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 登录验证
 */
@Slf4j
@RestController
@RequestMapping
public class SysLoginController {

    @Autowired
    private ISysMenuService menuService;
    @Autowired
    private TokenService tokenService;
    @Resource
    private AuthenticationManager authenticationManager;
    @Autowired
    private SysPermissionService permissionService;

    /**
     * 登录方法
     * 
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public BaseResponse login(@RequestBody LoginRequest loginBody) {
        BaseResponse response = BaseResponse.success();
        String userName = loginBody.getUserName();
        String password = loginBody.getPassword();
        String code = loginBody.getCode();
        String uuid = loginBody.getUuid();
        // 生成令牌
        // 验证码校验
        validateCaptcha(userName, code, uuid);
        // 登录前置校验
        loginPreCheck(userName, password);
        // 用户验证
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
//            if (e instanceof BadCredentialsException) {
//                throw new UserPasswordNotMatchException();
//            } else {
                throw new ServiceException(e.getMessage());
//            }
        } finally {
            AuthenticationContextHolder.clearContext();
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUserId());
        // 生成token
        String token = tokenService.createToken(loginUser);
        response.setData(token);
        return response;
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public BaseResponse getInfo() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        // 角色集合
//        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
//        Set<String> permissions = permissionService.getMenuPermission(user);
        BaseResponse ajax = BaseResponse.success();
//        ajax.put("user", user);
//        ajax.put("roles", roles);
//        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public BaseResponse getRouters() {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return BaseResponse.success(menuService.buildMenus(menus));
    }


    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    private void validateCaptcha(String username, String code, String uuid) {
//        boolean captchaEnabled = configService.selectCaptchaEnabled();
//        if (captchaEnabled) {
//            String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
//            String captcha = redisCache.getCacheObject(verifyKey);
//            redisCache.deleteObject(verifyKey);
//            if (captcha == null) {
//                throw new CaptchaExpireException();
//            }
//            if (!code.equalsIgnoreCase(captcha)) {
//                throw new CaptchaException();
//            }
//        }
    }


    /**
     * 登录前置校验
     * @param username 用户名
     * @param password 用户密码
     */
    private void loginPreCheck(String username, String password) {
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            throw new UserPasswordNotMatchException();
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            throw new UserPasswordNotMatchException();
        }
        // IP黑名单校验
//        String blackStr = configService.selectConfigByKey("sys.login.blackIPList");
//        if (IpUtils.isMatchedIp(blackStr, IpUtils.getIpAddr())) {
//            throw new BlackListException();
//        }
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
//        sysUser.setLoginIp(IpUtils.getIpAddr());
//        sysUser.setLoginDate(DateUtils.getNowDate());
//        userService.updateUserProfile(sysUser);
    }
}

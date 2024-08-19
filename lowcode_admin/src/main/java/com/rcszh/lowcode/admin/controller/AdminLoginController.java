package com.rcszh.lowcode.admin.controller;

import com.rcszh.lowcode.admin.dto.LoginRequest;
import com.rcszh.lowcode.common.vo.BaseResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
public class AdminLoginController {
    @ResponseBody
    @PostMapping("/login")
    public BaseResponse login(LoginRequest loginRequest){
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        // 通过账号密码生成token
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return BaseResponse.success();
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (!e.getMessage().isEmpty()) {
                msg = e.getMessage();
            }
            return BaseResponse.error(msg);
        }
    }
}

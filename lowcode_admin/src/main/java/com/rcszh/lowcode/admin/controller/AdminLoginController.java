package com.rcszh.lowcode.admin.controller;

import com.rcszh.lowcode.account.entity.Role;
import com.rcszh.lowcode.account.entity.User;
import com.rcszh.lowcode.account.service.RoleService;
import com.rcszh.lowcode.account.service.TenantService;
import com.rcszh.lowcode.account.service.UserService;
import com.rcszh.lowcode.admin.dto.LoginRequest;
import com.rcszh.lowcode.admin.vo.LoginUserInfoVo;
import com.rcszh.lowcode.common.vo.BaseResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminLoginController {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private TenantService tenantService;


    @ResponseBody
    @PostMapping("/login")
    public BaseResponse login(LoginRequest loginRequest){
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        return BaseResponse.success();
    }

    @ResponseBody
    @PostMapping("/getInfo")
    public BaseResponse getLoginInfo(){
        LoginUserInfoVo.LoginUserInfoVoBuilder userInfoBuilder = LoginUserInfoVo.builder();
        User user = userService.findUserByUserName("admin");
        tenantService.findTenantByCode(user.getTenantCode());
        Long[] roleIds = user.getRoleIds();
        List<Role> roles = roleService.findRoleByIds(List.of(roleIds));
        userInfoBuilder.userInfo(user);
        return BaseResponse.success(userInfoBuilder.build());
    }
}

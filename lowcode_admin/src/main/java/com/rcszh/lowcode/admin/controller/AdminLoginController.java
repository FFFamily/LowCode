//package com.rcszh.lowcode.admin.controller;
//
//import com.rcszh.lowcode.account.service.RoleService;
//import com.rcszh.lowcode.account.service.TenantService;
//import com.rcszh.lowcode.account.service.UserService;
//import com.rcszh.lowcode.admin.dto.LoginRequest;
//import com.rcszh.lowcode.admin.service.AdminLoginService;
//import com.rcszh.lowcode.admin.vo.LoginUserInfoVo;
//import com.rcszh.lowcode.common.vo.BaseResponse;
//import com.rcszh.lowcode.framework.TestService;
//import jakarta.annotation.Resource;
//import org.springframework.web.bind.annotation.*;
//
//@RequestMapping("/admin")
//@RestController
//public class AdminLoginController {
//
//    @Resource
//    private UserService userService;
//    @Resource
//    private TestService testService;
//    @Resource
//    private RoleService roleService;
//    @Resource
//    private TenantService tenantService;
//    @Resource
//    private AdminLoginService adminLoginService;
//
//    /**
//     * TODO 要求支持多种支持登录方式
//     */
//    @ResponseBody
//    @PostMapping("/login")
//    public BaseResponse login(LoginRequest loginRequest){
//        LoginUserInfoVo.LoginUserInfoVoBuilder userInfoBuilder = LoginUserInfoVo.builder();
//        String userName = loginRequest.getUserName();
//        String password = loginRequest.getPassword();
//        String token =  adminLoginService.login(userName,password);
//        userInfoBuilder.token("daasd12sadczc");
//        return BaseResponse.success(userInfoBuilder.build());
//    }
//
//    @ResponseBody
//    @GetMapping("/getInfo")
//    public BaseResponse getLoginInfo(){
//        LoginUserInfoVo.LoginUserInfoVoBuilder userInfoBuilder = LoginUserInfoVo.builder();
//        User user = userService.findUserByUserName("admin");
////        tenantService.findTenantByCode(user.getTenantCode());
////        Long[] roleIds = user.getRoleIds();
////        List<Role> roles = roleService.findRoleByIds(List.of(roleIds));
//        userInfoBuilder.user(user);
//        return BaseResponse.success(userInfoBuilder.build());
//    }
//
//    @ResponseBody
//    @PostMapping("/logout")
//    public BaseResponse logout(){
//        return BaseResponse.success();
//    }
//}

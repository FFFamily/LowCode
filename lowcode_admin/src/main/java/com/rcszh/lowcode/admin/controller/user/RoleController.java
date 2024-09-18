//package com.rcszh.lowcode.admin.controller;
//
//import com.github.pagehelper.PageInfo;
//import com.rcszh.lowcode.account.service.RoleService;
//import com.rcszh.lowcode.account.service.UserService;
//import com.rcszh.lowcode.common.constant.AccountConstants;
//import com.rcszh.lowcode.common.controller.BaseController;
//import com.rcszh.lowcode.common.vo.BaseResponse;
//import jakarta.annotation.Resource;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/admin/role")
//public class RoleController extends BaseController {
//    @Resource
//    private RoleService roleService;
//
//    @Resource
//    private UserService userService;
//
//    /**
//     * 新增保存角色
//     */
//    @PostMapping("/add")
//    @ResponseBody
//    public BaseResponse addRole(@Validated Role role) {
//        String roleName = role.getName();
//        String roleKey = role.getPermissionKey();
//        if (AccountConstants.ROLE_NAME_NOT_UNIQUE.equals(roleService.checkRoleNameUnique(roleName))) {
//            return BaseResponse.error("新增角色'" + role.getName() + "'失败，角色名称已存在");
//        } else if (AccountConstants.ROLE_KEY_NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(roleKey))) {
//            return BaseResponse.error("新增角色'" + role.getName() + "'失败，角色权限已存在");
//        }
//        roleService.insertRole(role);
//        return BaseResponse.success();
//    }
//
//    /**
//     * 列表查询
//     */
//    @GetMapping("/list")
//    @ResponseBody
//    public BaseResponse list(Role role) {
//        startPage();
//        List<Role> list = roleService.selectRoleList(role);
//        PageInfo<Role> pageInfo = new PageInfo<>(list);
//        return BaseResponse.success(pageInfo);
//    }
//
//}

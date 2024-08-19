package com.rcszh.lowcode.admin.controller;

import com.rcszh.lowcode.account.entity.Role;
import com.rcszh.lowcode.account.service.RoleService;
import com.rcszh.lowcode.account.service.UserService;
import com.rcszh.lowcode.common.constant.AccountConstants;
import com.rcszh.lowcode.common.vo.BaseResponse;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/admin/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @Resource
    private UserService userService;

    /**
     * 新增保存角色
     */
    @PostMapping("/add")
    @ResponseBody
    public BaseResponse addRole(@Validated Role role) {
        String roleName = role.getName();
        String roleKey = role.getKey();
        if (AccountConstants.ROLE_NAME_NOT_UNIQUE.equals(roleService.checkRoleNameUnique(roleName))) {
            return BaseResponse.error("新增角色'" + role.getName() + "'失败，角色名称已存在");
        } else if (AccountConstants.ROLE_KEY_NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(roleKey))) {
            return BaseResponse.error("新增角色'" + role.getName() + "'失败，角色权限已存在");
        }
        roleService.insertRole(role);
        return BaseResponse.success();
    }
}

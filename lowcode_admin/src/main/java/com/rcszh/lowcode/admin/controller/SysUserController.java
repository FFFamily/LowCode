package com.rcszh.lowcode.admin.controller;


import com.github.pagehelper.PageInfo;
import com.rcszh.lowcode.account.entity.SysPost;
import com.rcszh.lowcode.account.entity.core.SysDept;
import com.rcszh.lowcode.account.entity.core.SysRole;
import com.rcszh.lowcode.account.entity.core.SysUser;
import com.rcszh.lowcode.account.service.ISysDeptService;
import com.rcszh.lowcode.account.service.ISysPostService;
import com.rcszh.lowcode.account.service.ISysRoleService;
import com.rcszh.lowcode.account.service.ISysUserService;
import com.rcszh.lowcode.common.annotation.Log;
import com.rcszh.lowcode.common.controller.BaseController;
import com.rcszh.lowcode.common.enums.BusinessType;
import com.rcszh.lowcode.common.utils.SecurityUtils;
import com.rcszh.lowcode.account.utils.UserUtil;
import com.rcszh.lowcode.common.vo.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ISysPostService postService;

    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list")
    public BaseResponse list(SysUser user) {
        notSupAdminSetTenant(user);
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return BaseResponse.success(new PageInfo<>(list));
    }

    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:user:query')")
    @GetMapping(value = {"/", "/{userId}"})
    public BaseResponse getInfo(@PathVariable(value = "userId", required = false) Long userId) {
        userService.checkUserDataScope(userId);
        if (userId == null) {
            // 不传递userId默认使用当前登录userId
            userId = SecurityUtils.getUserId();
        }
        SysUser user = userService.selectUserById(userId);
        String userType = user.getType();
        SysRole sysRole = new SysRole();
        if (!UserUtil.isAdmin(userId, userType)) {
            sysRole.setTenantId(user.getTenantId());
        }
        List<SysRole> roles = roleService.selectRoleList(sysRole);

        SysPost sysPost = new SysPost();
        if (!UserUtil.isAdmin(userId, userType)) {
            sysPost.setTenantId(user.getTenantId());
        }
        List<SysPost> posts = postService.selectPostList(sysPost);
        HashMap<String, Object> result = new HashMap<>();
        result.put("roles", roles);
        result.put("posts", posts);
        if (userId != null) {
            SysUser sysUser = userService.selectUserById(userId);
            result.put("user", sysUser);
            result.put("postIds", postService.selectPostListByUserId(userId));
//            result.put("roleIds", sysUser.getRoles().stream().map(SysRole::getId).collect(Collectors.toList()));
        }
        return BaseResponse.success(result);
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public BaseResponse add(@Validated @RequestBody SysUser user) {
        if (!userService.checkUserNameUnique(user)) {
            return BaseResponse.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        } else if (user.getPhoneNumber() != null && !userService.checkPhoneUnique(user)) {
            return BaseResponse.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if ((user.getEmail() != null && !userService.checkEmailUnique(user))) {
            return BaseResponse.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        if (user.getDeptId() == null) {
            return BaseResponse.error("必须为新增用户选择一个所属机构");
        }
        SysDept sysDept = deptService.selectDeptById(user.getDeptId());
        user.setTenantId(sysDept.getTenantId());
//        user.setCreateBy(SecurityUtils.getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return BaseResponse.success(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public BaseResponse edit(@Validated @RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getId());
        if (!userService.checkUserNameUnique(user)) {
            return BaseResponse.error("修改用户'" + user.getUserName() + "'失败，登录账号已存在");
        } else if (user.getPhoneNumber() != null && !userService.checkPhoneUnique(user)) {
            return BaseResponse.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (user.getEmail() != null && !userService.checkEmailUnique(user)) {
            return BaseResponse.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
//        user.setUpdateBy(SecurityUtils.getUsername());
        return BaseResponse.success(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public BaseResponse remove(@PathVariable Long[] userIds) {
        if (Arrays.stream(userIds).anyMatch(item -> item.equals(SecurityUtils.getUserId()))) {
            return BaseResponse.error("当前用户不能删除");
        }
        return BaseResponse.success(userService.deleteUserByIds(userIds));
    }

    /**
     * 重置密码
     */
    @PreAuthorize("@ss.hasPermi('system:user:resetPwd')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public BaseResponse resetPwd(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getId());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
//        user.setUpdateBy(SecurityUtils.getUsername());
        return BaseResponse.success(userService.resetPwd(user));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public BaseResponse changeStatus(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getId());
//        user.setUpdateBy(SecurityUtils.getUsername());
        return BaseResponse.success(userService.updateUserStatus(user));
    }

    /**
     * 根据用户编号获取授权角色
     */
    @PreAuthorize("@ss.hasPermi('system:user:query')")
    @GetMapping("/authRole/{userId}")
    public BaseResponse authRole(@PathVariable("userId") Long userId) {
        SysUser user = userService.selectUserById(userId);
        List<SysRole> roles = roleService.selectRolesByUserId(userId);
        HashMap<Object, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("roles", UserUtil.isAdmin(user.getId(), user.getType()) ? roles : roles.stream().filter(r -> r.getTenantId().equals(user.getTenantId())).collect(Collectors.toList()));
        return BaseResponse.success(result);
    }

    /**
     * 用户授权角色
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.GRANT)
    @PutMapping("/authRole")
    public BaseResponse insertAuthRole(Long userId, Long[] roleIds) {
        userService.checkUserDataScope(userId);
        userService.insertUserAuth(userId, roleIds);
        return BaseResponse.success();
    }

    /**
     * 获取部门树列表
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/deptTree")
    public BaseResponse deptTree(SysDept dept) {
        notSupAdminSetTenant(dept);
        return BaseResponse.success(deptService.selectDeptTreeList(dept));
    }
}

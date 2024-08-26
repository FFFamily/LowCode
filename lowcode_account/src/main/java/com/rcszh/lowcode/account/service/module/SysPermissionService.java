package com.rcszh.lowcode.account.service.module;

import com.rcszh.lowcode.account.entity.core.SysUser;
import com.rcszh.lowcode.account.service.ISysMenuService;
import com.rcszh.lowcode.account.service.ISysRoleService;
import com.rcszh.lowcode.account.utils.UserUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户权限处理
 */
@Component
public class SysPermissionService {
    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysMenuService menuService;

    /**
     * 获取角色数据权限
     * 
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysUser user) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (UserUtil.isAdmin(user)) {
            roles.add("admin");
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getId()));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     * 
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user) {
        Set<String> perms = new HashSet<String>();
        perms.add("*:*:*");
        // 管理员拥有所有权限
//        if (UserUtil.isAdmin(user)) {
//            perms.add("*:*:*");
//        } else {
//            List<SysRole> roles = user.getRoles();
//            if (!roles.isEmpty() && roles.size() > 1) {
//                // 多角色设置permissions属性，以便数据权限匹配权限
//                for (SysRole role : roles) {
//                    Set<String> rolePerms = menuService.selectMenuPermsByRoleId(role.getRoleId());
//                    role.setPermissions(rolePerms);
//                    perms.addAll(rolePerms);
//                }
//            } else {
//                perms.addAll(menuService.selectMenuPermsByUserId(user.getUserId()));
//            }
//        }
        return perms;
    }
}

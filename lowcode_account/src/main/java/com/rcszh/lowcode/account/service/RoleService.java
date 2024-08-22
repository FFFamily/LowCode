//package com.rcszh.lowcode.account.service;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.rcszh.lowcode.account.mapper.RoleMapper;
//import jakarta.annotation.Resource;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class RoleService {
//    @Resource
//    private RoleMapper roleMapper;
//    /**
//     * 检验角色名称
//     */
//    public String checkRoleNameUnique(String roleName) {
//        return null;
//    }
//
//    public String checkRoleKeyUnique(String roleKey) {
//        return null;
//    }
//
//    public void insertRole(Role role) {
//
//    }
//
//    /**
//     * 根据角色id列表查询
//     */
//    public List<Role> findRoleByIds(List<Long> roleIds) {
//        if (roleIds == null || roleIds.isEmpty()) {
//            return List.of();
//        }
//        return roleMapper.selectList(new LambdaQueryWrapper<Role>()
//                .in(Role::getId, roleIds));
//    }
//
//    /**
//     * 查询角色列表
//     */
//    public List<Role> selectRoleList(Role role) {
//        return roleMapper.selectList(null);
//    }
//}

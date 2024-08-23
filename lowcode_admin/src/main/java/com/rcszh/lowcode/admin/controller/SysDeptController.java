//package com.rcszh.lowcode.admin.controller;
//
//import com.rcszh.lowcode.account.entity.core.SysDept;
//import com.rcszh.lowcode.account.service.ISysDeptService;
//import com.rcszh.lowcode.common.annotation.Log;
//import com.rcszh.lowcode.common.constant.UserConstants;
//import com.rcszh.lowcode.common.controller.BaseController;
//import com.rcszh.lowcode.common.enums.BusinessType;
//import com.rcszh.lowcode.common.utils.SecurityUtils;
//import com.rcszh.lowcode.common.utils.StringUtils;
//import com.rcszh.lowcode.common.vo.BaseResponse;
//import org.apache.commons.lang3.ArrayUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * 部门信息
// *
// * @author ruoyi
// */
//@RestController
//@RequestMapping("/system/dept")
//public class SysDeptController extends BaseController
//{
//    @Autowired
//    private ISysDeptService deptService;
//
//    /**
//     * 获取部门列表
//     */
//    @PreAuthorize("@ss.hasPermi('system:dept:list')")
//    @GetMapping("/list")
//    public BaseResponse list(SysDept dept) {
//        notSupAdminSetTenant(dept);
//        List<SysDept> depts = deptService.selectDeptList(dept);
//        return success(depts);
//    }
//
//    /**
//     * 查询部门列表（排除节点）
//     */
//    @PreAuthorize("@ss.hasPermi('system:dept:list')")
//    @GetMapping("/list/exclude/{deptId}")
//    public BaseResponse excludeChild(@PathVariable(value = "deptId", required = false) Long deptId) {
//        List<SysDept> depts = deptService.selectDeptList(new SysDept());
//        depts.removeIf(d -> d.getId().intValue() == deptId || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), deptId + ""));
//        return success(depts);
//    }
//
//    /**
//     * 根据部门编号获取详细信息
//     */
//    @PreAuthorize("@ss.hasPermi('system:dept:query')")
//    @GetMapping(value = "/{deptId}")
//    public BaseResponse getInfo(@PathVariable Long deptId) {
//        deptService.checkDeptDataScope(deptId);
//        return success(deptService.selectDeptById(deptId));
//    }
//
//    /**
//     * 新增部门
//     */
//    @PreAuthorize("@ss.hasPermi('system:dept:add')")
//    @Log(title = "部门管理", businessType = BusinessType.INSERT)
//    @PostMapping
//    public BaseResponse add(@Validated @RequestBody SysDept dept) {
//        if (!deptService.checkDeptNameUnique(dept)) {
//            return error("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
//        }
//        dept.setCreateBy(SecurityUtils.getUsername());
//        return BaseResponse.success(deptService.insertDept(dept));
//    }
//
//    /**
//     * 修改部门
//     */
//    @PreAuthorize("@ss.hasPermi('system:dept:edit')")
//    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public BaseResponse edit(@Validated @RequestBody SysDept dept) {
//        Long deptId = dept.getId();
//        deptService.checkDeptDataScope(deptId);
//        if (!deptService.checkDeptNameUnique(dept)) {
//            return error("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
//        }
//        else if (dept.getParentId().equals(deptId))
//        {
//            return error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
//        }
//        else if (StringUtils.equals(UserConstants.DEPT_DISABLE, dept.getStatus()) && deptService.selectNormalChildrenDeptById(deptId) > 0)
//        {
//            return error("该部门包含未停用的子部门！");
//        }
//        dept.setUpdateBy(SecurityUtils.getUsername());
//        return BaseResponse.success(deptService.updateDept(dept));
//    }
//
//    /**
//     * 删除部门
//     */
//    @PreAuthorize("@ss.hasPermi('system:dept:remove')")
//    @Log(title = "部门管理", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{deptId}")
//    public BaseResponse remove(@PathVariable Long deptId)
//    {
//        if (deptService.hasChildByDeptId(deptId))
//        {
//            return error("存在下级部门,不允许删除");
//        }
//        if (deptService.checkDeptExistUser(deptId))
//        {
//            return error("部门存在用户,不允许删除");
//        }
//        deptService.checkDeptDataScope(deptId);
//        return BaseResponse.success(deptService.deleteDeptById(deptId));
//    }
//}

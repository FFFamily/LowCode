
package com.rcszh.lowcode.admin.controller;


import com.github.pagehelper.PageInfo;
import com.rcszh.lowcode.account.entity.core.SysTenant;
import com.rcszh.lowcode.account.service.ISysTenantService;
import com.rcszh.lowcode.account.model.CreateTenantBody;
import com.rcszh.lowcode.common.annotation.Log;
import com.rcszh.lowcode.common.controller.BaseController;
import com.rcszh.lowcode.common.enums.BusinessType;
import com.rcszh.lowcode.common.vo.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 租户Controller
 */
@RestController
@RequestMapping("/system/tenant")
public class SysTenantController extends BaseController {
    @Autowired
    private ISysTenantService sysTenantService;

    /**
     * 查询租户列表
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:list')")
    @GetMapping("/list")
    public BaseResponse list(SysTenant sysTenant) {
        startPage();
        List<SysTenant> list = sysTenantService.selectSysTenantList(sysTenant);
        PageInfo<SysTenant> pageInfo = new PageInfo<>(list);
        return BaseResponse.success(pageInfo);
    }



    /**
     * 获取租户详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:query')")
    @GetMapping(value = "/{tenantId}")
    public BaseResponse getInfo(@PathVariable("tenantId") Long tenantId) {
        return BaseResponse.success(sysTenantService.selectSysTenantByTenantId(tenantId));
    }

    /**
     * 新增租户
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:add')")
    @Log(title = "租户", businessType = BusinessType.INSERT)
    @PostMapping
    public BaseResponse add(@RequestBody CreateTenantBody createTenantBody) {
        return BaseResponse.success(sysTenantService.insertSysTenant(createTenantBody));
    }

    /**
     * 修改租户
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:edit')")
    @Log(title = "租户", businessType = BusinessType.UPDATE)
    @PutMapping
    public BaseResponse edit(@RequestBody SysTenant sysTenant) {
        return BaseResponse.success(sysTenantService.updateSysTenant(sysTenant));
    }

    /**
     * 删除租户
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:remove')")
    @Log(title = "租户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tenantId}")
    public BaseResponse remove(@PathVariable Long tenantId) {
        return BaseResponse.success(sysTenantService.deleteSysTenantByTenantId(tenantId));
    }
}

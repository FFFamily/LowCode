package com.rcszh.lowcode.account.service;


import com.rcszh.lowcode.account.entity.core.SysTenant;
import com.rcszh.lowcode.account.model.CreateTenantBody;

import java.util.List;

/**
 * 租户Service接口
 */
public interface ISysTenantService {
    /**
     * 查询租户
     *
     * @param tenantId 租户主键
     * @return 租户
     */
     SysTenant selectSysTenantByTenantId(Long tenantId);

    /**
     * 查询租户列表
     *
     * @param sysTenant 租户
     * @return 租户集合
     */
     List<SysTenant> selectSysTenantList(SysTenant sysTenant);

    /**
     * 新增租户
     *
     * @param createTenantBody 租户
     * @return 结果
     */
    int insertSysTenant(CreateTenantBody createTenantBody);

    /**
     * 修改租户
     *
     * @param sysTenant 租户
     * @return 结果
     */
    int updateSysTenant(SysTenant sysTenant);
    /**
     * 删除租户信息
     *
     * @param tenantId 租户主键
     * @return 结果
     */
     int deleteSysTenantByTenantId(Long tenantId);
}

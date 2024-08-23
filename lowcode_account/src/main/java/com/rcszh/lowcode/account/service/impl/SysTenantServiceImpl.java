package com.rcszh.lowcode.account.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rcszh.lowcode.account.entity.core.SysDept;
import com.rcszh.lowcode.account.entity.core.SysTenant;
import com.rcszh.lowcode.account.entity.core.SysUser;
import com.rcszh.lowcode.account.mapper.SysDeptMapper;
import com.rcszh.lowcode.account.mapper.SysTenantMapper;
import com.rcszh.lowcode.account.mapper.SysUserMapper;
import com.rcszh.lowcode.account.model.CreateTenantBody;
import com.rcszh.lowcode.account.service.ISysTenantService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 租户Service业务层处理
 */
@Service
public class SysTenantServiceImpl implements ISysTenantService {
    @Resource
    private SysTenantMapper sysTenantMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysDeptMapper sysDeptMapper;

    /**
     * 查询租户
     *
     * @param tenantId 租户主键
     * @return 租户
     */
    @Override
    public SysTenant selectSysTenantByTenantId(Long tenantId) {
        return sysTenantMapper.selectById(tenantId);
    }

    /**
     * 查询租户列表
     *
     * @param sysTenant 租户
     * @return 租户
     */
    @Override
    public List<SysTenant> selectSysTenantList(SysTenant sysTenant) {
        return sysTenantMapper.selectList(new LambdaQueryWrapper<>());
    }

    /**
     * 新增租户
     *
     * @param createTenantBody 租户
     * @return 结果
     */
    @Override
    @Transactional
    public int insertSysTenant(CreateTenantBody createTenantBody) {
        // 生成用户
        SysUser sysUser =  new SysUser();
        sysUser.setNickName(createTenantBody.getUserName());
        sysUser.setUserName(createTenantBody.getUserName());
        // 生成部门
        SysDept sysDept =  new SysDept();
        sysDept.setDeptName(createTenantBody.getDeptName());
        sysDept.setAncestors("0");
        // 生成租户
        SysTenant sysTenant =  new SysTenant();
        sysTenant.setRemark(createTenantBody.getRemark());
        // 配置关联关系
        sysUser.setDeptId(sysDept.getId());
        sysUser.setTenantId(sysTenant.getId());
        sysDept.setTenantId(sysTenant.getId());
        sysDept.setUserId(sysUser.getId());
        sysTenant.setUserId(sysUser.getId());
        sysTenant.setDeptId(sysDept.getId());

        sysUserMapper.insertUser(sysUser);
        sysDeptMapper.insertDept(sysDept);
        return sysTenantMapper.insertSysTenant(sysTenant);
    }

    /**
     * 修改租户
     *
     * @param sysTenant 租户
     * @return 结果
     */
    @Override
    public int updateSysTenant(SysTenant sysTenant) {
        return sysTenantMapper.updateById(sysTenant);
    }


    /**
     * 删除租户信息
     *
     * @param tenantId 租户主键
     * @return 结果
     */
    @Override
    public int deleteSysTenantByTenantId(Long tenantId) {
        return sysTenantMapper.deleteById(tenantId);
    }
}

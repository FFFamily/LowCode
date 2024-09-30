package com.rcszh.framework.service;

import com.rcszh.framework.entity.SalaryGroup;
import com.rcszh.framework.mapper.SalaryGroupMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryGroupService {
    @Resource
    private SalaryGroupMapper salaryGroupMapper;
    /**
     * 添加模块
     */
    public void addGroup(SalaryGroup salaryGroup) {
        salaryGroupMapper.insert(salaryGroup);
    }

    public List<SalaryGroup> getAllGroup() {
        return salaryGroupMapper.selectList(null);
    }
}

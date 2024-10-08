package com.rcszh.lowcode.salary.service;

import com.rcszh.lowcode.salary.entity.SalaryGroup;
import com.rcszh.lowcode.salary.mapper.SalaryGroupMapper;
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

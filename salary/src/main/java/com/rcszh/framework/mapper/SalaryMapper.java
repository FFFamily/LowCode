package com.rcszh.framework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rcszh.framework.entity.Salary;
import com.rcszh.framework.entity.SalaryConfig;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SalaryMapper extends BaseMapper<Salary> {
}

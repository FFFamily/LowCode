package com.rcszh.lowcode.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rcszh.lowcode.core.entity.form.FormTable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoreDataSourceTableMapper extends BaseMapper<FormTable> {
}

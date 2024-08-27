package com.rcszh.lowcode.core.service;

import com.rcszh.lowcode.core.mapper.CoreDataSourceTableMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CoreDataSourceTableService {
    @Resource
    private CoreDataSourceTableMapper coreDataSourceTableMapper;
}

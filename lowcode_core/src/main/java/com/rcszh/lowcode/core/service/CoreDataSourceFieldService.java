package com.rcszh.lowcode.core.service;

import com.rcszh.lowcode.core.mapper.CoreDataSourceFieldMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CoreDataSourceFieldService {
    @Resource
    private CoreDataSourceFieldMapper coreDataSourceFieldMapper;
}

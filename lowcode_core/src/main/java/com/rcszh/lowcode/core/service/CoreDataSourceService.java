package com.rcszh.lowcode.core.service;

import com.rcszh.lowcode.core.mapper.CoreDataSourceMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CoreDataSourceService {
    @Resource
    private CoreDataSourceMapper coreDataSourceMapper;
}

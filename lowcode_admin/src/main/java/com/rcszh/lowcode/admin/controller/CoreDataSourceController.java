package com.rcszh.lowcode.admin.controller;

import com.rcszh.lowcode.core.service.CoreDataSourceFieldService;
import com.rcszh.lowcode.core.service.CoreDataSourceService;
import com.rcszh.lowcode.core.service.CoreDataSourceTableService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/data_source")
public class CoreDataSourceController {
    @Resource
    private CoreDataSourceService coreDataSourceService;
    @Resource
    private CoreDataSourceFieldService coreDataSourceFieldService;
    @Resource
    private CoreDataSourceTableService coreDataSourceTableService;
}

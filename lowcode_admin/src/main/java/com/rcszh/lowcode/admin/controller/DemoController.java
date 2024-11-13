package com.rcszh.lowcode.admin.controller;

import com.rcszh.lowcode.orm.ORM;
import com.rcszh.lowcode.orm.factory.ORMFactory;
import com.rcszh.lowcode.orm.factory.OrmConfig;
import com.rcszh.lowcode.orm.params.SelectWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DemoController {
    @Autowired
    private ORMFactory ormFactory;
    @GetMapping("/demo/test")
    public void test(){
        List<Map<String, Object>> maps = ormFactory.createORM(
                OrmConfig.config().tableName("test")
        ).selectList(new SelectWrapper()
                .eq("id", "1"));
        System.out.println(maps);
    }

}

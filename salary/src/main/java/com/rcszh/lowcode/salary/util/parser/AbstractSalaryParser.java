package com.rcszh.lowcode.salary.util.parser;

import com.rcszh.lowcode.account.entity.FakeUser;
import com.rcszh.lowcode.salary.entity.SalaryConfig;
import com.rcszh.lowcode.salary.enums.SalaryConfigTypeEnum;
import com.rcszh.lowcode.salary.schema.SalaryDataSchema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractSalaryParser {


    public void init(FakeUser fakeUser,List<SalaryConfig> configList){

    }

    /**
     * 解析
     */
    public abstract SalaryDataSchema doParser(SalaryConfig salaryConfig,HashMap<String, SalaryDataSchema> properties);
}

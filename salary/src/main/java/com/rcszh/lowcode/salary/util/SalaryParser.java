package com.rcszh.lowcode.salary.util;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.rcszh.lowcode.account.entity.FakeUser;
import com.rcszh.lowcode.salary.entity.Salary;
import com.rcszh.lowcode.salary.entity.SalaryConfig;
import com.rcszh.lowcode.salary.enums.SalaryConfigFormulaTypeEnum;
import com.rcszh.lowcode.salary.enums.SalaryConfigMappingTargetTypeEnum;
import com.rcszh.lowcode.salary.enums.SalaryConfigTypeEnum;
import com.rcszh.lowcode.salary.schema.SalaryConfigFormulaSchema;
import com.rcszh.lowcode.salary.schema.SalaryConfigMappingSchema;
import com.rcszh.lowcode.salary.schema.SalaryDataSchema;
import com.rcszh.lowcode.salary.util.parser.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
@Component
public class SalaryParser {
    @Resource
    private FixedValueParser fixedValueParser;
    @Resource
    private ManualInputParser manualInputParser;
    @Resource
    private FormulaParser formulaParser;
    @Resource
    private DataMappingParser dataMappingParser;
    @Resource
    private SystemConfigParser systemConfigParser;

    public Salary parserSalary(FakeUser fakeUser,List<SalaryConfig> configList) {
        HashMap<String, SalaryDataSchema> properties = new HashMap<>();
        List<SalaryDataSchema> result = new ArrayList<>();
        for (SalaryConfig salaryConfig : configList) {
            String type = salaryConfig.getType();
            SalaryDataSchema salaryDataSchema = null;
            if (SalaryConfigTypeEnum.FIXED_VALUE.getType().equals(type)) {
                salaryDataSchema = fixedValueParser.doParser(salaryConfig, properties);
            }else if (SalaryConfigTypeEnum.MANUAL_INPUT.getType().equals(type)){
                salaryDataSchema = manualInputParser.doParser(salaryConfig, properties);
            }else if (SalaryConfigTypeEnum.FORMULA.getType().equals(type)){
                salaryDataSchema = formulaParser.doParser(salaryConfig, properties);
            }else if (SalaryConfigTypeEnum.DATA_MAPPING.getType().equals(type)){
                salaryDataSchema = dataMappingParser.doParser(salaryConfig, properties);
            }else if (SalaryConfigTypeEnum.SYSTEM_CONFIG.getType().equals(type)){
                salaryDataSchema = systemConfigParser.doParser(salaryConfig, properties);
            }
            result.add(salaryDataSchema);
        }
        Salary salary = new Salary();
        salary.setData(JSONUtil.toJsonStr(result));
        return salary;
    }

}

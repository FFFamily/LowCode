package com.rcszh.lowcode.salary.util.parser;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.rcszh.lowcode.salary.entity.SalaryConfig;
import com.rcszh.lowcode.salary.enums.SalaryConfigFormulaTypeEnum;
import com.rcszh.lowcode.salary.schema.SalaryConfigFormulaSchema;
import com.rcszh.lowcode.salary.schema.SalaryDataSchema;
import com.rcszh.lowcode.salary.util.SalaryParser;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

/**
 * 手动输入
 */
@Component
public class ManualInputParser extends AbstractSalaryParser {
    @Override
    public SalaryDataSchema doParser(SalaryConfig salaryConfig, HashMap<String, SalaryDataSchema> properties) {
        String type = salaryConfig.getType();
        SalaryDataSchema salaryDataSchema = new SalaryDataSchema();
        salaryDataSchema.setFieldName(salaryConfig.getName());
        properties.put(salaryDataSchema.getFieldName(), salaryDataSchema);
        return salaryDataSchema;
    }
}

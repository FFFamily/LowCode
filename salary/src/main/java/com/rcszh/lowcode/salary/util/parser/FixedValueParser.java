package com.rcszh.lowcode.salary.util.parser;

import com.rcszh.lowcode.salary.entity.SalaryConfig;
import com.rcszh.lowcode.salary.schema.SalaryDataSchema;
import com.rcszh.lowcode.salary.util.SalaryParser;
import org.springframework.stereotype.Component;

import java.util.HashMap;
@Component
public class FixedValueParser extends AbstractSalaryParser {

    @Override
    public SalaryDataSchema doParser(SalaryConfig salaryConfig, HashMap<String, SalaryDataSchema> properties) {
        String type = salaryConfig.getType();
        SalaryDataSchema salaryDataSchema = new SalaryDataSchema();
        // 固定值
        salaryDataSchema.setFieldName(salaryConfig.getName());
        salaryDataSchema.setFieldValue(salaryConfig.getValue());
        properties.put(salaryDataSchema.getFieldName(), salaryDataSchema);
        return salaryDataSchema;
    }
}

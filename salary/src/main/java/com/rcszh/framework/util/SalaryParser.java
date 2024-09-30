package com.rcszh.framework.util;

import com.rcszh.framework.entity.SalaryConfig;
import com.rcszh.framework.enums.SalaryConfigTypeEnum;
import com.rcszh.framework.schema.SalaryDataSchema;

import java.util.HashMap;
import java.util.Objects;

public class SalaryParser {
    protected HashMap<String, SalaryDataSchema> properties = new HashMap<>();

    public void parser(SalaryConfig salaryConfig) {
        String type = salaryConfig.getType();
        if (SalaryConfigTypeEnum.FIXED_VALUE.getType().equals(type)) {
            SalaryDataSchema salaryDataSchema = new SalaryDataSchema();
            salaryDataSchema.setFieldName(salaryConfig.getName());
            salaryDataSchema.setFieldValue(salaryConfig.getValue());
            properties.put(salaryDataSchema.getFieldName(), salaryDataSchema);
        }else if (SalaryConfigTypeEnum.MANUAL_INPUT.getType().equals(type)){
            SalaryDataSchema salaryDataSchema = new SalaryDataSchema();
            salaryDataSchema.setFieldName(salaryConfig.getName());
            properties.put(salaryDataSchema.getFieldName(), salaryDataSchema);
        }
        else if (SalaryConfigTypeEnum.MANUAL_INPUT.getType().equals(type)){
            SalaryDataSchema salaryDataSchema = new SalaryDataSchema();
            salaryDataSchema.setFieldName(salaryConfig.getName());
            properties.put(salaryDataSchema.getFieldName(), salaryDataSchema);
        }
    }
}

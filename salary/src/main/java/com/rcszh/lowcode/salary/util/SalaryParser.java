package com.rcszh.lowcode.salary.util;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.rcszh.lowcode.salary.entity.SalaryConfig;
import com.rcszh.lowcode.salary.enums.SalaryConfigFormulaTypeEnum;
import com.rcszh.lowcode.salary.enums.SalaryConfigTypeEnum;
import com.rcszh.lowcode.salary.schema.SalaryConfigFormulaSchema;
import com.rcszh.lowcode.salary.schema.SalaryDataSchema;

import java.math.BigDecimal;
import java.util.*;

public class SalaryParser {

    public static List<SalaryDataSchema> parserList(List<SalaryConfig> configList) {
        HashMap<String, SalaryDataSchema> properties = new HashMap<>();
        List<SalaryDataSchema> result = new ArrayList<>();
        for (SalaryConfig salaryConfig : configList) {
            result.add(doParser(salaryConfig, properties));
        }
        return result;
    }

    /**
     * 解析
     */
    public static SalaryDataSchema doParser(SalaryConfig salaryConfig,HashMap<String, SalaryDataSchema> properties) {
        String type = salaryConfig.getType();
        SalaryDataSchema salaryDataSchema = new SalaryDataSchema();
        if (SalaryConfigTypeEnum.FIXED_VALUE.getType().equals(type)) {
            // 固定值
            salaryDataSchema.setFieldName(salaryConfig.getName());
            salaryDataSchema.setFieldValue(salaryConfig.getValue());
            properties.put(salaryDataSchema.getFieldName(), salaryDataSchema);
        }else if (SalaryConfigTypeEnum.MANUAL_INPUT.getType().equals(type)){
            // 录入
            salaryDataSchema.setFieldName(salaryConfig.getName());
            properties.put(salaryDataSchema.getFieldName(), salaryDataSchema);
        }else if (SalaryConfigTypeEnum.FORMULA.getType().equals(type)){
            // 公式计算
            salaryDataSchema.setFieldName(salaryConfig.getName());
            JSONArray array = JSONUtil.parseArray(salaryConfig.getValue());
            Deque<BigDecimal> ops = new LinkedList<>();
            BigDecimal num = BigDecimal.ZERO;
            for (int i = 0; i < array.size(); i++) {
                // 公式遍历
                JSONObject jsonObject = array.getJSONObject(i);
                SalaryConfigFormulaSchema formulaSchema = jsonObject.toBean(SalaryConfigFormulaSchema.class);
                String formulaType = formulaSchema.getFormulaType();
                String formulaValue = formulaSchema.getFormulaValue();
                if (SalaryConfigFormulaTypeEnum.PARAM.getType().equals(formulaType)){
                    num = num.multiply(BigDecimal.TEN).add(new BigDecimal(formulaSchema.getFormulaValue()));
                }
                if (SalaryConfigFormulaTypeEnum.OPERATOR.getType().equals(formulaType)){
                    if (Objects.equals(formulaValue, "+")) {
                        ops.push(num);
                    } else if (Objects.equals(formulaValue, "-")) {
                        ops.push(BigDecimal.ZERO.subtract(num));
                    }else if (Objects.equals(formulaValue, "*")) {
                        ops.push(ops.pop().multiply(num) );
                    }else if (Objects.equals(formulaValue, "/")) {
                        ops.push(ops.pop().divide(num));
                    }
                    num = BigDecimal.ZERO;
                }
            }
            BigDecimal ans = BigDecimal.ZERO;
            while (!ops.isEmpty()) {
                ans = ans.add(ops.pop()) ;
            }
            salaryDataSchema.setFieldValue(ans.toString());
            properties.put(salaryDataSchema.getFieldName(), salaryDataSchema);
        }
        return salaryDataSchema;
    }

}

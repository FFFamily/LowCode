package com.rcszh.lowcode.salary.util;

import lombok.Data;

import java.util.Comparator;
import java.util.List;

/**
 * 旷工规则
 */
public class AbsenteeUtil {
    public static Number doParser(List<AbsenteeConfig> configs,Integer absenteeNumber,Number baseSalary){
        configs.sort(Comparator.comparingInt(a -> a.getMin().intValue()));
        Number result = null;
        for (AbsenteeConfig config : configs) {
            String minSymbol = config.getMinSymbol();
            Number min = config.getMin();
            if (minSymbol.equals(">")){
                if (absenteeNumber <= min.intValue()){
                    continue;
                }
            }else if (minSymbol.equals(">=")){
                if (absenteeNumber < min.intValue()){
                    continue;
                }
            }else {
                throw new RuntimeException("错误");
            }
            String maxSymbol = config.getMaxSymbol();
            Number max = config.getMax();
            if (maxSymbol.equals("<")){
                if (absenteeNumber >= max.intValue()){
                    continue;
                }
            }else if (maxSymbol.equals("<=")){
                if (absenteeNumber > max.intValue()){
                    continue;
                }
            }else {
                throw new RuntimeException("错误");
            }
            result = config.getDeductionRatio();
            break;
        }
        if (result != null) {
            return result.doubleValue() * baseSalary.doubleValue();
        }
        throw new RuntimeException("旷工规则计算错误");
    }

    @Data
    public static class AbsenteeConfig{
        // 成绩
        private Number score;
        private Number min;
        private String minSymbol;
        private Number max;
        private String maxSymbol;
        // 扣除比例
        private Number deductionRatio;
    }
}

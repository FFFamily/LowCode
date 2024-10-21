package com.rcszh.lowcode.salary.util;

import lombok.Data;

import java.util.Comparator;
import java.util.List;

/**
 * 迟到规则
 */
public class LateUtil {
    /**
     *
     * @param configs 迟到配置
     * @param lateTime 迟到时长
     * @param baseSalary 基础薪资
     * @return
     */
    public static Number doParser(List<LateConfig> configs,Integer lateTime,Number baseSalary){
        configs.sort(Comparator.comparingInt(a -> a.getMin().intValue()));
        for (LateConfig config : configs) {
            String minSymbol = config.getMinSymbol();
            Number min = config.getMin();
            if (minSymbol.equals(">")){
                if (lateTime <= min.intValue()){
                    continue;
                }
            }else if (minSymbol.equals(">=")){
                if (lateTime < min.intValue()){
                    continue;
                }
            }else {
                throw new RuntimeException("错误");
            }

            String maxSymbol = config.getMaxSymbol();
            Number max = config.getMax();
            if (maxSymbol.equals("<")){
                if (lateTime >= max.intValue()){
                    continue;
                }
            }else if (maxSymbol.equals("<=")){
                if (lateTime > max.intValue()){
                    continue;
                }
            }else {
                throw new RuntimeException("错误");
            }
            String valueType = config.getValueType();
            Number value = config.getValue();
            if (valueType.equals("固定值")){
                return value;
            }else if (valueType.equals("百分比")){
                return value.doubleValue() * baseSalary.doubleValue();
            }else {
                throw new RuntimeException("错误");
            }
        }
        throw new RuntimeException("迟到规则解析错误");
    }
    @Data
    public static class LateConfig{
        private Number min;
        private String minSymbol;
        private Number max;
        private String maxSymbol;
        private Number value;
        private String valueType;
    }
}

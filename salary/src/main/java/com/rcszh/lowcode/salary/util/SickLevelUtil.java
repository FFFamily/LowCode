package com.rcszh.lowcode.salary.util;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 病假规则
 */
public class SickLevelUtil {
    /**
     *
     * @param configs 病假配置
     * @param employmentDate 入职日期
     * @param sickLevelDate 病假日期
     * @param baseSalary 基础工资
     * @return 病假薪资
     */
    public static Number doParser(List<SickLevelConfig> configs, Date employmentDate,Date sickLevelDate,Number baseSalary){
        configs.sort(Comparator.comparingInt(a -> a.getMin().intValue()));
        Integer year = DateUtil.year(sickLevelDate) -  DateUtil.year(employmentDate);
        Number result = null;
        for (SickLevelConfig config : configs) {
            String minSymbol = config.getMinSymbol();
            Number min = config.getMin();
            if (minSymbol.equals(">")){
                if (year <= min.intValue()){
                    continue;
                }
            }else if (minSymbol.equals(">=")){
                if (year < min.intValue()){
                    continue;
                }
            }else {
                throw new RuntimeException("错误");
            }
            String maxSymbol = config.getMaxSymbol();
            Number max = config.getMax();
            if (maxSymbol.equals("<")){
                if (year >= max.intValue()){
                    continue;
                }
            }else if (maxSymbol.equals("<=")){
                if (year > max.intValue()){
                    continue;
                }
            }else {
                throw new RuntimeException("错误");
            }
            result = config.getRate();
            break;
        }
        if (result != null) {
            return result.doubleValue() * baseSalary.doubleValue();
        }
        throw new RuntimeException("旷工规则计算错误");
    }

    @Data
    public static class SickLevelConfig{
        private Number min;
        private String minSymbol;
        private Number max;
        private String maxSymbol;
        private Number rate;
    }
}

package com.rcszh.lowcode.salary.util;

import cn.hutool.core.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

/**
 * 加班规则
 * todo 应该还有一个加班配置
 */
public class WorkOverTimeUtil {
    @Getter
    @AllArgsConstructor
    public  enum WorkOverTimeConfigTypeEnum{
         workDay(0),
        // 休息日加班支付比例
         restDay(1),
        // 节假日加班支付比例
        holiday(2);
        private final Integer type;
    }

    public static Number doParser(WorkOverTimeConfig config,WorkOverTimeConfigTypeEnum typeEnum,Integer workTime,Number  baseSalary){
        switch (typeEnum){
            case workDay -> {
                return config.getWorkDaySalaryRatio().doubleValue() * baseSalary.doubleValue() * workTime;
            }
            case holiday -> {
                return config.getHolidaySalaryRatio().doubleValue() * baseSalary.doubleValue() * workTime;
            }
            case restDay -> {
                return config.getRestDaySalaryRatio().doubleValue() * baseSalary.doubleValue() * workTime;
            }
        }
        throw new RuntimeException("加班解析错误");
    }

    @Data
    public static class WorkOverTimeConfig{
        private Number  workDaySalaryRatio;
        // 休息日加班支付比例
        private Number restDaySalaryRatio;
        // 节假日加班支付比例
        private Number holidaySalaryRatio;
    }

    /**
     *
     */
    public static class WorkOverTimeRuleConfig{
        private String deptId;

    }
}

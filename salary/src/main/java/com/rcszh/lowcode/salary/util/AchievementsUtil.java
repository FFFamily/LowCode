package com.rcszh.lowcode.salary.util;

import lombok.Data;

import java.util.List;

/**
 * 绩效转换
 */
public class AchievementsUtil {

    /**
     * 未完善
     * @param configs 绩效配置
     * @param level 绩效等级
     * @param achievementsSalary 绩效工资
     * @param month 当前工资月份
     * @return
     */
    public static Number doParser(List<AchievementConfig> configs,QuarterAchievementConfig quarterAchievementConfig,String level, Integer month,Number achievementsSalary){
        AchievementConfig config = configs.stream().filter(item -> item.getLevel().equals(level)).findFirst().orElseThrow();
        Number bonusRatio = config.getBonusRatio();
        Double monthAchievementSalary =  bonusRatio.doubleValue() * achievementsSalary.doubleValue();
        List<Integer> months = quarterAchievementConfig.getMonths();
        // TODO 当月不是季度的时候怎么算
        if (months.stream().anyMatch(item -> item.equals(month))){
            // 对应月份才计算季度绩效
            List<QuarterAchievementConfigItem> configItems = quarterAchievementConfig.getConfigItems();
            QuarterAchievementConfigItem quarterAchievementConfigItem = configItems.stream().filter(item -> item.getLevel().equals(level)).findFirst().orElseThrow();
            Number quarterAchievementSalary = quarterAchievementConfigItem.getBonusRatio();
            return monthAchievementSalary + quarterAchievementSalary.doubleValue() * achievementsSalary.doubleValue();
        }
        return monthAchievementSalary;
    }

    @Data
    public static class AchievementConfig{
        //等级
        private String level;
        // 成绩
        private Number score;
        private Number min;
        private String minSymbol;

        private Number max;
        private String maxSymbol;
        // 奖金比例
        private Number bonusRatio;
    }
    @Data
    public static class QuarterAchievementConfig{
        // 需要计算季度绩效的月份
        private List<Integer> months;
        private List<QuarterAchievementConfigItem> configItems;

    }

    @Data
    public static class QuarterAchievementConfigItem{
        //等级
        private String level;
        // 奖金比例
        private Number bonusRatio;
    }
}

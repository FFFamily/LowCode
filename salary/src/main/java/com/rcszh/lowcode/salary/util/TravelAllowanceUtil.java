package com.rcszh.lowcode.salary.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.relational.core.sql.In;

import java.util.List;
import java.util.Optional;

public class TravelAllowanceUtil {
    public static Number doParser(List<TravelConfig> configs,String deptId,String rankId,TravelBusinessEnum travelBusinessEnum){
        Optional<TravelConfig> config = configs
                .stream()
                .filter(item -> item.getDeptId().equals(deptId) && item.getRankId().equals(rankId))
                .findFirst();
        if (config.isEmpty()){
            return 0;
        }
        switch (travelBusinessEnum){
            case domestic -> {
                return config.get().getDomesticBusinessSubsidy();
            }
            case province -> {
                return config.get().getProvinceBusinessSubsidy();
            }
            case abroad -> {
                return config.get().getAbroadBusinessSubsidy();
            }
        }
        throw new RuntimeException("错误");
    }

    @Getter
    @AllArgsConstructor
    public enum TravelBusinessEnum{
        domestic(0),
        province(1),
        abroad(2)
        ;
        private final Integer type;
    }
    @Data
    public static class TravelConfig{
        // 部门id
        private String deptId;
        // 职级id
        private String rankId;
        // 国内补贴
        private Number domesticBusinessSubsidy;
        // 省内出差
        private Number provinceBusinessSubsidy;
        // 国外出差
        private Number abroadBusinessSubsidy;
    }
}

package com.rcszh.lowcode.salary.dto;

import com.rcszh.lowcode.salary.entity.SalaryConfig;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
public class SalaryCheckDto {
    /**
     * 编号
     */
    private String id;
    /**
     * 考勤月份
     */
    private String month;
    /**
     * 对应月份的考勤情况
     */
    private List<Attendance> attendance;
    /**
     * 薪资单项配置
     */
    private List<SalaryConfig> salaryConfig;
    /**
     * 迟到配置
     */
    private List<LateConfig> lateConfig;
    /**
     * 旷工配置
     */
    private AbsenteeConfig absenteeConfig;
    /**
     * 节假日加班
     */
    private WorkOverTimeConfig workOverTimeConfig;
    /**
     * 病假配置
     */
    private List<SickLeaveConfig> sickLeaveConfigs;

    /**
     * 迟到配置
     */
    @Data
    private static class LateConfig{
        private Integer min;
        private String minType;
        private Integer max;
        private String maxType;
        private BigDecimal value;
        private String valueType;
    }

    /**
     * 旷工配置
     */
    private static class AbsenteeConfig{
        // 旷工基础时间单位
        private Integer AbsenteeTimeUnit;
        /**
         * 旷工半天扣除工资比例
         */
        private Integer  halfDayAbsenteeism;
        // 旷工一天扣除工资比例
        private Integer allDayAbsenteeism;
        // 多次旷工配置
        private List<AbsenteeDayConfig> absenteeDayConfig;
    }
    private static class AbsenteeDayConfig{
        private Integer min;
        private Integer max;
        private BigDecimal value;
    }

    private static class WorkOverTimeConfig{
        // 工作日加班支付比例
        private Number  workDaySalaryRatio;
        // 休息日加班支付比例
        private Number restDaySalaryRatio;
        // 节假日加班支付比例
        private Number holidayDaySalaryRatio;
    }


    private static class SickLeaveConfig{
        private Integer min;
        private String minType;
        private Integer max;
        private String maxType;
        private BigDecimal value;
        private String valueType;
    }

    /**
     * 考勤
     */
    @Data
    private static class Attendance {
        /**
         * 考勤日期：2024-07-01
         */
        private Date attendanceDate;
        /**
         * 标准工作时长：分钟
         */
        private Integer standardWorkTime;
        /**
         * 实际工作时长：分钟
         */
        private Integer actualWorkTime;
        /**
         * 迟到时长
         */
        private Integer lateTime;
        /**
         * 旷工时长
         */
        private Integer abstainedTime;
        /**
         * 早退时长
         */
        private Integer leaveEarlyTime;
        /**
         * 工作日加班时长
         */
        private Integer workOverTime;
        /**
         * 节假日加班时长
         */
        private Integer holidayOverTime;
        /**
         * 年假
         */
        private Integer annualHolidayTime;
        /**
         * 病假
         */
        private Integer sickLeaveTime;
        /**
         * 调休假
         */
        private Integer compensatoryLeaveTime;
        /**
         * 婚假
         */
        private Integer marriageHolidayTime;
        /**
         * 产假
         */
        private Integer maternityLeaveTime;
        /**
         * 陪产假
         */
        private Integer paternityLeaveTime;
        /**
         * 其他假期
         */
        private Integer otherLeaveTime;
    }
}

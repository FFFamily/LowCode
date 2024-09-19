package com.rcszh.lowcode.core.schema;

import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * 表单动作配置Json解析schema
 */
@Data
public class FormActionConfigSchema {
    /**
     * 数据配置
     */
    private List<DetailTableSchema> dataList;
    @Data
    public static class DetailTableSchema {
        /**
         * 字段名
         */
        private String fieldName;
        /**
         * 赋值方式: FormActionAssignmentModeEnum
         */
        private String assignmentMode;
        /**
         * 字段值
         */
        private String fieldValue;
    }
}

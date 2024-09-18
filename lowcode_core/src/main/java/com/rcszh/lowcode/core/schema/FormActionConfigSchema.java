package com.rcszh.lowcode.core.schema;

import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * 表单动作配置Json解析schema
 */
@Data
public class FormActionConfigSchema {
    private List<DetailTableSchema> dataList;
    @Data
    private static class DetailTableSchema {
        private String fieldId;
        private String fieldValue;
    }
}

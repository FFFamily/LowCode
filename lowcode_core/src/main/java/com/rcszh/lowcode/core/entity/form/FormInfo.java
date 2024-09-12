package com.rcszh.lowcode.core.entity.form;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 整个表单信息
 */
@Data
public class FormInfo {
    /**
     * 表单信息
     */
    private Form form;
    /**
     * 表单对应的库表信息
     */
    private List<FormTable> formTables;
    /**
     * 库表字段信息
     */
    private Map<String,List<FormTableField>> fields;
}

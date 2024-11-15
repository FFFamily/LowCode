package com.rcszh.lowcode.core.entity.dto;

import com.rcszh.lowcode.core.dto.FormTableFieldDto;
import com.rcszh.lowcode.core.dto.form.FormTableDto;
import com.rcszh.lowcode.core.entity.form.Form;
import com.rcszh.lowcode.core.entity.form.FormTable;
import com.rcszh.lowcode.core.entity.form.FormTableField;
import lombok.Data;

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
    private List<FormTableDto> formTables;
    /**
     * 库表字段信息
     */
//    private Map<String,List<FormTableFieldDto>> fields;
}

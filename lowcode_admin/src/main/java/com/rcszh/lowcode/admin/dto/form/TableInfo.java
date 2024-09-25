package com.rcszh.lowcode.admin.dto.form;

import com.rcszh.lowcode.core.entity.form.FormTable;
import com.rcszh.lowcode.core.entity.form.FormTableField;
import lombok.Data;

import java.util.List;

@Data
public class TableInfo {
    private FormTable table;
    private List<FormTableField> fields;
}

package com.rcszh.lowcode.core.dto.form;

import cn.hutool.core.bean.BeanUtil;
import com.rcszh.lowcode.core.dto.FormTableFieldDto;
import com.rcszh.lowcode.core.entity.form.FormTable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class FormTableDto extends FormTable {
    private List<FormTableFieldDto> children;

    public static FormTableDto convertToDto(FormTable formTable) {
        FormTableDto dto = new FormTableDto();
        BeanUtil.copyProperties(formTable, dto);
        return dto;
    }

    public FormTable changeToFormTable() {
        this.setChildren(null);
        return this;
    }
}

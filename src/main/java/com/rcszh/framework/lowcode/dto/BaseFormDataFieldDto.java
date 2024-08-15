package com.rcszh.framework.lowcode.dto;

import com.rcszh.framework.lowcode.entity.FormDataField;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

/**
 * 前端传递数据字段基础Dto
 */
public class BaseFormDataFieldDto extends FormDataField {
    /**
     * 是否强制删除
     */
    private Boolean forceDeletion;
}

package com.rcszh.lowcode.core.dto.view;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class ViewFormConfigFindDto {
    // 视图id
    private String viewFormId;
    // 配置类型
    private String viewConfigParentType;
}

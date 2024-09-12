package com.rcszh.lowcode.admin.dto;

import com.rcszh.lowcode.core.entity.form.ViewFormConfig;
import lombok.Data;

import java.util.List;

@Data
public class ShowListRequest {
    /**
     * 列表配置
     */
    private List<ViewFormConfig> listViewFormConfigs;
}

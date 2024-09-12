package com.rcszh.lowcode.core.entity.form;

import lombok.Data;

import java.util.List;
@Data
public class ViewFormInfo {
    private Form form;
    private List<ViewForm> viewForms;
    private List<ViewFormConfig> viewFormConfigs;
}

package com.rcszh.lowcode.core.entity.dto;

import com.rcszh.lowcode.core.entity.form.Form;
import com.rcszh.lowcode.core.entity.view.ViewForm;
import com.rcszh.lowcode.core.entity.view.ViewFormConfig;
import lombok.Data;

import java.util.List;
@Data
public class ViewFormInfo {
    private Form form;
    private List<ViewForm> viewForms;
    private List<ViewFormConfig> viewFormConfigs;
}

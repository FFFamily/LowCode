package com.rcszh.lowcode.core.entity.dto;

import com.rcszh.lowcode.core.entity.action.FormAction;
import com.rcszh.lowcode.core.entity.action.FormActionCondition;
import com.rcszh.lowcode.core.entity.action.FormCondition;
import lombok.Data;

import java.util.List;

@Data
public class FormActionInfo {
    private FormCondition formCondition;
    private List<FormAction> formActions;
}

package com.rcszh.lowcode.core.service.action;

import com.rcszh.lowcode.core.entity.action.FormAction;
import com.rcszh.lowcode.core.mapper.action.FormActionConditionMapper;
import com.rcszh.lowcode.core.mapper.action.FormActionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormActionService {
    @Resource
    private FormActionMapper formActionMapper;
    @Resource
    private FormActionConditionMapper formActionConditionMapper;
    public void addAction(FormAction formAction) {
        formActionMapper.insert(formAction);
    }

    public void findActionByConditionToMap(List<String> formConditionList) {

    }
}

package com.rcszh.lowcode.admin.controller.form;

import com.rcszh.lowcode.common.vo.BaseResponse;
import com.rcszh.lowcode.core.entity.dto.FormInfo;
import com.rcszh.lowcode.core.entity.view.ViewForm;
import com.rcszh.lowcode.core.entity.view.ViewFormConfig;
import com.rcszh.lowcode.core.service.form.FormService;
import com.rcszh.lowcode.core.service.form.FormTableService;
import com.rcszh.lowcode.core.service.view.ViewFormConfigService;
import com.rcszh.lowcode.core.service.view.ViewFormService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/form/table")
public class FormTableController {
    @Resource
    private FormTableService formTableService;
    /**
     * 表单列表
     */
    @ResponseBody
    @GetMapping("/all")
    public BaseResponse all() {
        return BaseResponse.success(formTableService.getAllFormTable());
    }

}

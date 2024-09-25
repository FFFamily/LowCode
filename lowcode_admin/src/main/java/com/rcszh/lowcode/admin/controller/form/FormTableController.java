package com.rcszh.lowcode.admin.controller.form;

import com.rcszh.lowcode.admin.dto.form.TableInfo;
import com.rcszh.lowcode.common.vo.BaseResponse;
import com.rcszh.lowcode.core.entity.dto.FormInfo;
import com.rcszh.lowcode.core.entity.form.Form;
import com.rcszh.lowcode.core.entity.form.FormTable;
import com.rcszh.lowcode.core.entity.form.FormTableField;
import com.rcszh.lowcode.core.entity.view.ViewForm;
import com.rcszh.lowcode.core.entity.view.ViewFormConfig;
import com.rcszh.lowcode.core.enums.FormTableTypeEnum;
import com.rcszh.lowcode.core.service.form.FormService;
import com.rcszh.lowcode.core.service.form.FormTableFieldService;
import com.rcszh.lowcode.core.service.form.FormTableService;
import com.rcszh.lowcode.core.service.view.ViewFormConfigService;
import com.rcszh.lowcode.core.service.view.ViewFormService;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/form/table")
public class FormTableController {
    @Resource
    private FormService formService;
    @Resource
    private FormTableService formTableService;
    @Resource
    private FormTableFieldService formTableFieldService;
    /**
     * 表单列表
     */
    @ResponseBody
    @GetMapping("/all")
    public BaseResponse all() {
        return BaseResponse.success(formTableService.getAllFormTable());
    }

    /**
     * 表单列表
     */
    @ResponseBody
    @GetMapping("/get/{tableId}")
    public BaseResponse getTableInfo(@PathVariable String tableId) {
        TableInfo tableInfo = new TableInfo();
        FormTable table = formTableService.getTableById(tableId);
        tableInfo.setTable(table);
        List<FormTableField> fields = formTableFieldService.getFieldByTable(tableId);
        tableInfo.setFields(fields);
        return BaseResponse.success(tableInfo);
    }

    /**
     * 创建表
     * 这种表的创建一般是明细表
     */
    @ResponseBody
    @PostMapping("/createChildTable")
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse create(@RequestBody FormTable formTable){
        Optional<FormTable> mainTable = formTableService.getTableByFormId(formTable.getFormId()).stream().filter(item -> item.getType().equals(FormTableTypeEnum.MAIN.getType())).findFirst();
        if (mainTable.isPresent()) {
            FormTable table = mainTable.get();
            formTableService.initChildFormTable(table.getTableName(),formTable);
            formTableFieldService.generateChildInitFields(table.getTableName(),formTable.getFormId(),formTable.getId());
        }else {
            throw new RuntimeException("主表不存在");
        }
        return BaseResponse.success();
    }

}

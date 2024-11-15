package com.rcszh.lowcode.core.service.form;

import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rcszh.lowcode.common.constant.CommonConstant;
import com.rcszh.lowcode.core.dto.FormTableFieldDto;
import com.rcszh.lowcode.core.dto.form.FormTableDto;
import com.rcszh.lowcode.core.entity.dto.FormInfo;
import com.rcszh.lowcode.core.entity.form.*;
import com.rcszh.lowcode.core.entity.view.ViewForm;
import com.rcszh.lowcode.core.entity.view.ViewFormConfig;
import com.rcszh.lowcode.core.enums.FormTableTypeEnum;
import com.rcszh.lowcode.core.enums.InterfaceTypeEnum;
import com.rcszh.lowcode.core.enums.SystemTypeEnum;
import com.rcszh.lowcode.core.enums.ViewFormTypeEnum;
import com.rcszh.lowcode.core.enums.form.FormStatusEnum;
import com.rcszh.lowcode.core.mapper.FormTableFieldMapper;
import com.rcszh.lowcode.core.mapper.form.FormMapper;
import com.rcszh.lowcode.core.service.view.ViewFormConfigService;
import com.rcszh.lowcode.core.service.view.ViewFormService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 表单
 */
@Service
public class FormService {
    @Resource
    private FormTableService formTableService;
    @Resource
    private FormTableFieldService formTableFieldService;
    @Resource
    private FormTableFieldMapper formTableFieldMapper;
    @Resource
    private ViewFormService viewFormService;
    @Resource
    private FormMapper formMapper;
    @Resource
    private ViewFormConfigService viewFormConfigService;

    /**
     * 首次创建表单
     */
    @Transactional(rollbackFor = Exception.class)
    public void createForm(FormInfo formInfo) {
        // 创建表单信息
        Form form = formInfo.getForm();
        if (form == null) {
            throw new RuntimeException();
        }
        // 不能出现同名表单
        Form oldForm = formMapper.selectOne(new LambdaQueryWrapper<Form>().eq(Form::getName, form.getName()));
        if (oldForm != null) {
            throw new RuntimeException("已存在相同名称的表单");
        }
        // 创建状态
        form.setFormStatus(FormStatusEnum.CREATED.getStatus());
        formMapper.insert(form);
        // 创建表单对应的库表信息
        Assert.notNull(formInfo.getFormTables());
        // 首次创建只会生成一个主表
        FormTable formTable = formInfo.getFormTables().getFirst();
        String tableName = formTable.getTableName();
        FormTable oldFormTable = formTableService.getFormTableByTableName(tableName);
        if (oldFormTable != null) {
            throw new RuntimeException("表单编码已存在");
        }
        formTable.setFormId(form.getId());
        formTable.setName(form.getName());
        formTable.setType(FormTableTypeEnum.MAIN.getType());
        formTableService.initMainFormTable(formTable);
        // 创建对应库表的字段
        formTableFieldService.generateInitFields(form.getId(),formTable.getId());
    }

    /**
     * 获取整个表单信息
     */
    public FormInfo getFormInfoById(String formId) {
        FormInfo formInfo = new FormInfo();
        Form form = formMapper.selectById(formId);
        List<FormTableDto> formTables = formTableService.getTableByFormId(formId).stream().map(FormTableDto::convertToDto).collect(Collectors.toList());
        Map<String, List<FormTableFieldDto>> tableFields = formTableFieldService.getFieldByFormIdToMap(formId);
        for (FormTableDto formTable : formTables) {
            formTable.setChildren(tableFields.get(formTable.getId()));
        }
        formInfo.setFormTables(formTables);
        formInfo.setForm(form);
//        formInfo.setFields(tableFields);
        return formInfo;
    }

    /**
     * 获取所有的表单
     */
    public List<Form> getAllForm() {
        return formMapper.selectList(null);
    }


    /**
     * 发布表单
     */
    @Transactional(rollbackFor = Exception.class)
    public void releaseForm(FormInfo formInfo) {
        Form form = formInfo.getForm();
        // 更新发布状态
        form.setFormStatus(FormStatusEnum.PUBLISH.getStatus());
        // 更新表单
        formMapper.updateById(form);
        // 更新表单表
        formTableService.batchUpdateFormTable(formInfo.getFormTables());
        // 更新表单字段信息
        Map<String, List<FormTableFieldDto>> fields = formInfo.getFormTables().stream().collect(Collectors.toMap(FormTable::getId,FormTableDto::getChildren));
        for (Map.Entry<String, List<FormTableFieldDto>> entry : fields.entrySet()) {
            String tableId = entry.getKey();
            // 找到字段对应的表
            FormTable formTable = formInfo.getFormTables().stream().filter(item -> item.getId().equals(tableId)).findFirst().orElse(null);
            if (formTable == null) {
                throw new RuntimeException("字段表格不正确");
            }
            // 拿到对应的库表名称
            String tableName = formTable.getTableName();
            // 字段生成
            formTableFieldService.createField(tableName,entry.getValue());
        }
        // 生成对应的视图
        List<ViewForm> configs = viewFormService.findAllByFormId(form.getId());
        if (configs == null || configs.isEmpty()) {
            // 添加查询视图
            // 添加视图的同时需要给表单绑定默认视图
            ViewForm viewForm = new ViewForm();
            viewForm.setFormId(form.getId());
            viewForm.setName("默认查看视图");
            viewForm.setType(ViewFormTypeEnum.VIEW_PAGE.getType());
            viewForm.setSystemType(SystemTypeEnum.BUILT_IN.getType());
            viewForm.setStatus(CommonConstant.YES);
            viewFormService.createViewFormConfig(viewForm);
            // 添加列表视图
            viewForm = new ViewForm();
            viewForm.setFormId(form.getId());
            viewForm.setName("默认列表视图");
            viewForm.setType(ViewFormTypeEnum.LIST_PAGE.getType());
            viewForm.setSystemType(SystemTypeEnum.BUILT_IN.getType());
            viewForm.setStatus(CommonConstant.YES);
            viewFormService.createViewFormConfig(viewForm);
        }
    }

    /**
     * 通过id查询
     */
    public Form getFormById(String formId) {
        return formMapper.selectById(formId);
    }
}

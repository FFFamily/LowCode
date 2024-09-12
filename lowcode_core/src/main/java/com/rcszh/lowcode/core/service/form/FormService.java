package com.rcszh.lowcode.core.service.form;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rcszh.lowcode.core.entity.form.*;
import com.rcszh.lowcode.core.enums.FormTableTypeEnum;
import com.rcszh.lowcode.core.enums.ViewFormTypeEnum;
import com.rcszh.lowcode.core.mapper.form.FormMapper;
import com.rcszh.lowcode.core.service.view.ViewFormConfigService;
import com.rcszh.lowcode.core.service.view.ViewFormService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
        Form oldForm = formMapper.selectOne(new LambdaQueryWrapper<Form>().eq(Form::getCode, form.getCode()));
        if (oldForm != null) {
            throw new RuntimeException("已存在");
        }
        formMapper.insert(form);
        // 创建表单对应的库表信息
        Assert.notNull(formInfo.getFormTables());
        // 首次创建只会生成一个主表
        FormTable formTable = formInfo.getFormTables().getFirst();
        formTable.setFormId(form.getId());
        formTable.setType(FormTableTypeEnum.MAIN.getType());
        formTableService.createFormData(formTable);
    }

    /**
     * 获取整个表单信息
     */
    public FormInfo getFormInfoById(String formId) {
        FormInfo formInfo = new FormInfo();
        Form form = formMapper.selectById(formId);
        List<FormTable> formTables = formTableService.getTableByFormId(formId);
        Map<String, List<FormTableField>> tableFields = formTableFieldService.getFieldByFormIdToMap(formId);
        formInfo.setFormTables(formTables);
        formInfo.setForm(form);
        formInfo.setFields(tableFields);
        return formInfo;
    }

    /**
     * 获取所有的表单
     */
    public List<Form> getAllForm() {
        return formMapper.selectList(null);
    }

    /**
     * 获取所有的
     */
    public List<ViewForm> getViewFormInfo(String formId) {
        return viewFormService.findAllConfigByFormId(formId);
    }

    /**
     * 发布表单
     */
    @Transactional(rollbackFor = Exception.class)
    public void releaseForm(FormInfo formInfo) {
        // 更新表单
        Form form = formInfo.getForm();
        formMapper.updateById(form);
        // 更新表单表
        formTableService.batchUpdateFormTable(formInfo.getFormTables());
        // 生成对应的视图
        List<ViewForm> configs = viewFormService.findAllConfigByFormId(form.getId());
        if (configs == null || configs.isEmpty()) {
            // 添加查询视图
            ViewForm viewForm = new ViewForm();
            viewForm.setFormId(form.getId());
            viewForm.setName("默认查看视图");
            viewForm.setType(ViewFormTypeEnum.VIEW_PAGE.getType());
            viewFormService.createViewFormConfig(viewForm);
            // 添加列表视图
            viewForm.setFormId(form.getId());
            viewForm.setName("默认列表视图");
            viewForm.setType(ViewFormTypeEnum.LIST_PAGE.getType());
            viewFormService.createViewFormConfig(viewForm);
        }
    }

    /**
     * 通过视图id获取配置
     */
    public List<ViewFormConfig> getViewFormConfigById(String viewFormId) {
        return viewFormConfigService.findAllConfigById(viewFormId);
    }
}

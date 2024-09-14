package com.rcszh.lowcode.core.service.view;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rcszh.lowcode.core.entity.form.ViewForm;
import com.rcszh.lowcode.core.entity.form.ViewFormConfig;
import com.rcszh.lowcode.core.enums.SystemTypeEnum;
import com.rcszh.lowcode.core.enums.ViewFormTypeEnum;
import com.rcszh.lowcode.core.mapper.form.ViewFormMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewFormService {
    @Resource
    private ViewFormMapper viewFormMapper;
    @Resource
    private ViewFormConfigService viewFormConfigService;
    /**
     * 查询表单下的所有视图配置
     */
    public List<ViewForm> findAllByFormId(String formId) {
        return viewFormMapper.selectList(new LambdaQueryWrapper<ViewForm>().eq(ViewForm::getFormId, formId));
    }

    /**
     * 创建视图配置
     */
    public void createViewFormConfig(ViewForm viewForm) {
        viewFormMapper.insert(viewForm);
        String type = viewForm.getType();
        String viewFormId = viewForm.getId();
        if (ViewFormTypeEnum.VIEW_PAGE.getType().equals(type)) {
            // 查看视图
            viewFormConfigService.createViewPageConfig(viewFormId);
        }else if (ViewFormTypeEnum.LIST_PAGE.getType().equals(type)){
            // 列表视图
            viewFormConfigService.createListPageConfig(viewFormId);
        }
    }

    /**
     * 查询对应类型的view表单配置
     * 默认查看内置
     */
    public List<ViewFormConfig> findConfigByType(String formId, String type) {
        // 拿到对应的视图
        ViewForm viewForm = viewFormMapper.selectOne(new LambdaQueryWrapper<ViewForm>()
                .eq(ViewForm::getFormId, formId)
                .eq(ViewForm::getType, type)
                .eq(ViewForm::getSystemType, SystemTypeEnum.BUILT_IN.getType()));
        // 拿到视图对应的配置
        return viewFormConfigService.findAllConfigById(viewForm.getId());
    }

    /**
     * 新增视图
     */
    public void addViewForm(ViewForm viewForm) {
        viewFormMapper.insert(viewForm);
    }
}

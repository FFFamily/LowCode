package com.rcszh.lowcode.core.service.view;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rcszh.lowcode.core.entity.form.ViewForm;
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
    public List<ViewForm> findAllConfigByFormId(String formId) {
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
}

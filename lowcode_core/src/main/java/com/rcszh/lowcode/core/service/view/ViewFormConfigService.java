package com.rcszh.lowcode.core.service.view;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rcszh.lowcode.core.entity.form.ViewFormConfig;
import com.rcszh.lowcode.core.enums.ViewFormConfigSystemTypeEnum;
import com.rcszh.lowcode.core.enums.ViewFormConfigTypeEnum;
import com.rcszh.lowcode.core.mapper.form.ViewFormConfigMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewFormConfigService {
    @Resource
    private ViewFormConfigMapper viewFormConfigMapper;

    /**
     * 首次创建查看视图配置
     */
    public void createViewPageConfig(String viewFormId) {
        // 新增按钮配置
        ViewFormConfig addviewFormConfig = new ViewFormConfig();
        addviewFormConfig.setViewFormId(viewFormId);
        addviewFormConfig.setType(ViewFormConfigTypeEnum.BUTTON.getType());
        addviewFormConfig.setName("新增");
        addviewFormConfig.setSystemType(ViewFormConfigSystemTypeEnum.BUILT_IN.getType());
        addviewFormConfig.setIsShow("Y");
        viewFormConfigMapper.insert(addviewFormConfig);
        // 编辑按钮配置
        ViewFormConfig editviewFormConfig = new ViewFormConfig();
        editviewFormConfig.setViewFormId(viewFormId);
        editviewFormConfig.setType(ViewFormConfigTypeEnum.BUTTON.getType());
        editviewFormConfig.setName("编辑");
        editviewFormConfig.setSystemType(ViewFormConfigSystemTypeEnum.BUILT_IN.getType());
        editviewFormConfig.setIsShow("Y");
        viewFormConfigMapper.insert(editviewFormConfig);
    }
    /**
     * 首次创建列表视图配置
     */
    public void createListPageConfig(String viewFormId) {

    }

    /**
     * 通过 viewFormId 查询对应视图的所有配置
     *
     * @return
     */
    public List<ViewFormConfig> findAllConfigById(String viewFormId) {
        return viewFormConfigMapper.selectList(new LambdaQueryWrapper<ViewFormConfig>().eq(ViewFormConfig::getViewFormId, viewFormId));
    }
}

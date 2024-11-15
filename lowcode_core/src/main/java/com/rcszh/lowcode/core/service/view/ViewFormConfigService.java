package com.rcszh.lowcode.core.service.view;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rcszh.lowcode.common.constant.CommonConstant;
import com.rcszh.lowcode.core.dto.view.ViewFormConfigDto;
import com.rcszh.lowcode.core.dto.view.ViewFormConfigFindDto;
import com.rcszh.lowcode.core.entity.view.ViewFormConfig;
import com.rcszh.lowcode.core.enums.SystemTypeEnum;
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
    public void createViewPageConfig(String systemType,String viewFormId) {
        // 新增按钮配置
        ViewFormConfig addviewFormConfig = new ViewFormConfig();
        addviewFormConfig.setViewFormId(viewFormId);
        addviewFormConfig.setParentType(ViewFormConfigTypeEnum.DETAIL_BUTTON.getParentType());
        addviewFormConfig.setChildType(ViewFormConfigTypeEnum.DETAIL_BUTTON.getChildType());
        addviewFormConfig.setName(ViewFormConfigTypeEnum.DETAIL_BUTTON.getName());
        addviewFormConfig.setSystemType(systemType);
        addviewFormConfig.setIsShow("Y");
        viewFormConfigMapper.insert(addviewFormConfig);
    }
    /**
     * 首次创建列表视图配置
     * 首次创建会生成的配置有：列表渲染配置、列表按钮配置
     */
    public void createListPageConfig(String systemType,String viewFormId) {
        // 列表显示配置
        ViewFormConfig addviewFormConfig = new ViewFormConfig();
        addviewFormConfig.setViewFormId(viewFormId);
        addviewFormConfig.setParentType(ViewFormConfigTypeEnum.LIST_SHOW.getParentType());
        addviewFormConfig.setChildType(ViewFormConfigTypeEnum.LIST_SHOW.getChildType());
        addviewFormConfig.setName(ViewFormConfigTypeEnum.LIST_SHOW.getName());
        addviewFormConfig.setSystemType(systemType);
        addviewFormConfig.setIsShow(CommonConstant.YES);
        viewFormConfigMapper.insert(addviewFormConfig);
        // 列表按钮配置
        ViewFormConfig buttonviewFormConfig = new ViewFormConfig();
        buttonviewFormConfig.setViewFormId(viewFormId);
        buttonviewFormConfig.setParentType(ViewFormConfigTypeEnum.LIST_BUTTON.getParentType());
        buttonviewFormConfig.setChildType(ViewFormConfigTypeEnum.LIST_BUTTON.getChildType());
        addviewFormConfig.setName(ViewFormConfigTypeEnum.LIST_BUTTON.getName());
        buttonviewFormConfig.setSystemType(SystemTypeEnum.BUILT_IN.getType());
        buttonviewFormConfig.setIsShow(CommonConstant.YES);
        viewFormConfigMapper.insert(buttonviewFormConfig);
    }

    /**
     * 通过条件查询对应视图的所有配置
     */
    public List<ViewFormConfig> findAllConfigById(ViewFormConfigFindDto findDto) {
        return viewFormConfigMapper.selectList(new LambdaQueryWrapper<ViewFormConfig>()
                .eq(findDto.getViewFormId() != null,ViewFormConfig::getViewFormId, findDto.getViewFormId())
                .eq(findDto.getViewConfigParentType() != null,ViewFormConfig::getParentType, findDto.getViewConfigParentType())
        );
    }

    /**
     * 更新对应的配置
     */
    public void updateViewFormConfig(ViewFormConfigDto viewFormConfig) {
        viewFormConfigMapper.updateById(viewFormConfig.convertToEntity());
    }
}

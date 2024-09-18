package com.rcszh.lowcode.core.utils;

import com.rcszh.lowcode.core.entity.view.ViewFormConfig;

import java.util.List;

/**
 * 视图配置工具类
 */
public class ViewConfigUtil {
    /**
     * 将显示配置转为SQL查询字段
     */
    public List<String> convertToSqlColumn(ViewFormConfig viewFormConfig) {
        String options = viewFormConfig.getOptions();
//        JSONUtil.parseArray(options).;
        return null;
    }
}

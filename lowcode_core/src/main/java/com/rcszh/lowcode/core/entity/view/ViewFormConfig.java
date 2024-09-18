package com.rcszh.lowcode.core.entity.view;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("view_form_config")
public class ViewFormConfig {
    @TableId(type= IdType.ASSIGN_UUID)
    private String id;
    /**
     * 视图id
     */
    private String viewFormId;
    /**
     * 配置类型：显示配置、搜搜配置、排序配置、 筛选配置、按钮配置、基础配置
     */
    private String type;
    /**
     * 配置名称
     */
    private String name;
    /**
     * 系统类型：内置|自定义
     */
    private String systemType;
    /**
     * 是否启用
     */
    private String isShow;
    /**
     * 显示配置（JSON）
     */
    private String showConfig;
    /**
     * 配置信息
     * 显示配置：[{fieldCode:字段编码,fieldName:字段名称,fieldType:字段组件类型}]
     * 搜索设置：[{fieldCode:字段编码,fieldName:字段名称,fieldType:字段组件类型}]
     * 排序设置：[{fieldCode:字段编码,fieldName:字段名称,fieldType:字段组件类型,order: 排序}]
     * 筛选设置：待定
     * 按钮设置：[{buttonId:按钮id}]
     * 基础设置：待定
     * 页面配置
     */
    private String options;

}

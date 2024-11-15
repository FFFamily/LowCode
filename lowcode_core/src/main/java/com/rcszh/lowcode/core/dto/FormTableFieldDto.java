package com.rcszh.lowcode.core.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.rcszh.lowcode.core.entity.form.FormTableField;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

@Data
public class FormTableFieldDto {
    /**
     * 字段组件id
     */
    private String id;
    /**
     * design- + id 拼接
     */
    private String designKey;
    /**
     * 表单Id
     */
    private String formId;
    /**
     * 表Id
     */
    private String formTableId;
    /**
     * 字段编码（对应真实数据库字段）
     * 原（code）
     */
    private String name;
    /**
     * 字段组件类型
     * type: input、text...
     */
    private String component;
    /**
     * 对应数据库类型
     * 原 type
     */
    private String jdbcType;
    /**
     * 字段组件名称
     * 原 name
     */
    private String label;
    /**
     * 状态
     */
    private String status;
    /**
     * 前端UI prop
     */
    private Object props;
    /**
     * 字段配置（UI）
     */
    private String options;
    /**
     * 字段索引
     */
    private String fieldIndex;
    /**
     * 组件描述
     */
    private String description;

    public static FormTableFieldDto coverToDto(FormTableField formTableField){
        FormTableFieldDto formTableFieldDto = new FormTableFieldDto();
        BeanUtil.copyProperties(formTableField, formTableFieldDto, CopyOptions.create().setIgnoreNullValue(true));
        formTableFieldDto.setProps(JSONUtil.toBean(formTableField.getProps(),Object.class));
        formTableFieldDto.setDesignKey("design-"+formTableField.getId());
        return formTableFieldDto;
    }

    public FormTableField coverToEntity(){
        FormTableField formTableField = new FormTableField();
        BeanUtil.copyProperties(this, formTableField, CopyOptions.create().setIgnoreNullValue(true));
        Optional.ofNullable(this.getProps()).ifPresent(value -> formTableField.setProps(JSONUtil.parse(value).toString()));
        return formTableField;
    }
}

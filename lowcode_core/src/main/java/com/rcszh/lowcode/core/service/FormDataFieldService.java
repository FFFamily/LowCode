//package com.rcszh.lowcode.core.service;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.rcszh.lowcode.core.entity.FormDataField;
//import jakarta.annotation.Resource;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class FormDataFieldService {
//    @Resource
//    private FormDataFieldMapper formDataFieldMapper;
//    /**
//     * 保存单个表单数据类型字段
//     */
//    public void saveOneFormDataField(FormDataField formDataFieldDto) {
//        // 校验字段编码
//        LambdaQueryWrapper<FormDataField> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(FormDataField::getFromDataTableId, formDataFieldDto.getFromDataTableId());
//        queryWrapper.eq(FormDataField::getCode, formDataFieldDto.getCode());
//        FormDataField oldFormDataField = formDataFieldMapper.selectOne(queryWrapper);
//        if (oldFormDataField != null) {
//            throw new BizException("重复的字段编码");
//        }
//        formDataFieldMapper.insert(formDataFieldDto);
//    }
//    /**
//     * 条件查询表单数据字段列表
//     */
//    public List<FormDataField> getFieldQueryList(FormDataField formDataFieldDto) {
//        return formDataFieldMapper.selectList(getBaseLambdaQueryWrapper(formDataFieldDto.getFromDataTableId())
//                .eq(formDataFieldDto.getName() != null,FormDataField::getName,formDataFieldDto.getName()));
//    }
//    /**
//     * 删除
//     */
//    public Object delOneFormDataField(BaseFormDataFieldDto formDataFieldDto) {
//        // todo 这里的删除要确认是要要强制删除数据库表单信息，目前是假删除
//        return formDataFieldMapper.deleteById(formDataFieldDto);
//    }
//
//    /**
//     * @param id              数据字段id
//     * @return 数据字段详情信息
//     */
//    public FormDataField getOneFormDataDetailInfoById(String id) {
//        return formDataFieldMapper.selectById(id);
//    }
//
//    /**
//     * 基础的 queryWrapper
//     * 默认就需要查询 当前字段配置的表单id
//     */
//    private LambdaQueryWrapper<FormDataField> getBaseLambdaQueryWrapper(String formDataTableId) {
//        LambdaQueryWrapper<FormDataField> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(FormDataField::getFromDataTableId, formDataTableId);
//        return queryWrapper;
//    }
//
//}

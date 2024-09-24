package com.rcszh.lowcode.core.service.form;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rcszh.lowcode.core.entity.FormDataField;
import com.rcszh.lowcode.core.entity.form.FormTableField;
import com.rcszh.lowcode.core.enums.FormTableFieldStatusEnum;
import com.rcszh.lowcode.core.enums.InterfaceTypeEnum;
import com.rcszh.lowcode.core.mapper.FormTableFieldMapper;
import com.rcszh.lowcode.orm.ORM;
import com.rcszh.lowcode.orm.SqlFieldConfig;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FormTableFieldService {
    @Resource
    private FormTableFieldMapper formTableFieldMapper;
//    /**
//     * 保存单个表单数据类型字段
//     */
//    public void saveOneFormDataField(FormDataField formDataFieldDto) {
//        // 校验字段编码
//        LambdaQueryWrapper<FormDataField> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(FormDataField::getFromDataTableId, formDataFieldDto.getFromDataTableId());
//        queryWrapper.eq(FormDataField::getCode, formDataFieldDto.getCode());
//        FormDataField oldFormDataField = formTableFieldMapper.selectOne(queryWrapper);
//        if (oldFormDataField != null) {
//            throw new BizException("重复的字段编码");
//        }
//        formTableFieldMapper.insert(formDataFieldDto);
//    }
//    /**
//     * 条件查询表单数据字段列表
//     */
//    public List<FormDataField> getFieldQueryList(FormDataField formDataFieldDto) {
//        return formTableFieldMapper.selectList(getBaseLambdaQueryWrapper(formDataFieldDto.getFromDataTableId())
//                .eq(formDataFieldDto.getName() != null,FormDataField::getName,formDataFieldDto.getName()));
//    }
//    /**
//     * 删除
//     */
//    public Object delOneFormDataField(BaseFormDataFieldDto formDataFieldDto) {
//        // todo 这里的删除要确认是要要强制删除数据库表单信息，目前是假删除
//        return formTableFieldMapper.deleteById(formDataFieldDto);
//    }
//
//    /**
//     * @param id              数据字段id
//     * @return 数据字段详情信息
//     */
//    public FormDataField getOneFormDataDetailInfoById(String id) {
//        return formTableFieldMapper.selectById(id);
//    }

    /**
     * 基础的 queryWrapper
     * 默认就需要查询 当前字段配置的表单id
     */
    private LambdaQueryWrapper<FormDataField> getBaseLambdaQueryWrapper(String formDataTableId) {
        LambdaQueryWrapper<FormDataField> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FormDataField::getFromDataTableId, formDataTableId);
        return queryWrapper;
    }

    public Map<String, List<FormTableField>> getFieldByFormIdToMap(String formId) {
        List<FormTableField> formTableFields = formTableFieldMapper.selectList(new LambdaQueryWrapper<FormTableField>().eq(FormTableField::getFormId, formId));
        return formTableFields.stream().collect(Collectors.groupingBy(FormTableField::getFormTableId));
    }

    /**
     * 生成数据库字段配置信息，同时生成真实库表字段
     */
    public void createField(String tableName, List<FormTableField> formTableFields) {
        // 更新真实的数据库字段
        ORM.orm().tableName(tableName)
                .updateTable(formTableFields.stream()
                // 过滤出新创建的字段
                // 只会生成 已保存||已创建 状态的字段
                .filter(item -> FormTableFieldStatusEnum.CREATED.getStatus().equals(item.getStatus()) ||  FormTableFieldStatusEnum.SAVED.getStatus().equals(item.getStatus()))
                .map(item -> {
                    SqlFieldConfig sqlFieldConfig = new SqlFieldConfig();
                    sqlFieldConfig.setFieldName(item.getCode());
                    sqlFieldConfig.setFieldType(item.getType());
                    sqlFieldConfig.setIsNull(true);
                    return sqlFieldConfig;
        }).collect(Collectors.toList()));
        formTableFields.forEach(formTableField -> {
            if (formTableField.getId() != null){
                // TODO 只能更新名称，其他的不能更新
                formTableFieldMapper.updateById(formTableField);
            }else {
                // 若是新增字段那么需要更新其状态
                formTableField.setStatus(FormTableFieldStatusEnum.PUBLISHED.getStatus());
                formTableFieldMapper.insert(formTableField);
            }
        });
    }
    /**
     * 生成初始化字段
     * 当表单创建时默认自带的字段
     */
    public void generateChildInitFields(String mainTable, String formId, String formTableId) {
        generateInitFields(formId,formTableId);
        FormTableField formTableField = new FormTableField();
        formTableField.setFormId(formId);
        formTableField.setFormTableId(formTableId);
        // todo 这里的设置也是不可控制的，比如我修改了 ORM 那边的规则，忘记改这里的规则就会有问题
        formTableField.setCode(mainTable+"_id");
        formTableField.setInterfaceType(InterfaceTypeEnum.INPUT.getType());
        formTableField.setName(mainTable+"_ID");
        formTableField.setType("String");
        formTableField.setStatus("published");
        HashMap<Object, Object> options = new HashMap<>();
        options.put("x-component","Input");
        formTableField.setOptions(JSONUtil.parse(options).toString());
        formTableFieldMapper.insert(formTableField);
    }

    /**
     * 生成初始化字段
     * 当表单创建时默认自带的字段
     */
    public void generateInitFields(String formId, String formTableId) {
        FormTableField formTableField = new FormTableField();
        formTableField.setFormId(formId);
        formTableField.setFormTableId(formTableId);
        formTableField.setCode("id");
        formTableField.setInterfaceType(InterfaceTypeEnum.INPUT.getType());
        formTableField.setName("ID");
        formTableField.setType("String");
        formTableField.setStatus("published");
        HashMap<Object, Object> options = new HashMap<>();
        options.put("x-component","Input");
        formTableField.setOptions(JSONUtil.parse(options).toString());
        formTableFieldMapper.insert(formTableField);
        formTableField.setCode("createBy");
        formTableField.setName("创建人");
        formTableFieldMapper.insert(formTableField);
        formTableField.setCode("createAt");
        formTableField.setName("创建时间");
        formTableFieldMapper.insert(formTableField);
        formTableField.setCode("updateBy");
        formTableField.setName("更新人");
        formTableFieldMapper.insert(formTableField);
        formTableField.setCode("updateAt");
        formTableField.setName("更新时间");
        formTableFieldMapper.insert(formTableField);
    }
}

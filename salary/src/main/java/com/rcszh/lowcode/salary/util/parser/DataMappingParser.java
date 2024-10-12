package com.rcszh.lowcode.salary.util.parser;

import cn.hutool.json.JSONUtil;
import com.rcszh.lowcode.account.entity.FakeUser;
import com.rcszh.lowcode.account.service.FakeUserService;
import com.rcszh.lowcode.salary.entity.SalaryConfig;
import com.rcszh.lowcode.salary.enums.SalaryConfigMappingTargetTypeEnum;
import com.rcszh.lowcode.salary.schema.SalaryConfigMappingSchema;
import com.rcszh.lowcode.salary.schema.SalaryDataSchema;
import com.rcszh.lowcode.salary.util.SalaryParser;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * 数据映射
 */
@Component
public class DataMappingParser extends AbstractSalaryParser {
    @Resource
    private FakeUserService fakeUserService;
    private String userId;

    @Override
    public void init(FakeUser fakeUser, List<SalaryConfig> configList) {
        this.userId = fakeUser.getId();
    }

    @Override
    public SalaryDataSchema doParser(SalaryConfig salaryConfig, HashMap<String, SalaryDataSchema> properties) {
        String type = salaryConfig.getType();
        SalaryDataSchema salaryDataSchema = new SalaryDataSchema();
        // 数据映射
        salaryDataSchema.setFieldName(salaryConfig.getName());
        SalaryConfigMappingSchema schema = JSONUtil.toBean(salaryConfig.getValue(), SalaryConfigMappingSchema.class);
        String mappingTarget = schema.getMappingTarget();
        SalaryConfigMappingTargetTypeEnum targetType = SalaryConfigMappingTargetTypeEnum.getByTargetType(mappingTarget);
        if (targetType == null){
            throw new RuntimeException("不支持的类型");
        }
        // TODO 查询用户表
        String mappingProperties = schema.getMappingProperties();
        Object result;
        switch (targetType){
            case USER -> result = JSONUtil.parse(fakeUserService.getById(userId)).getByPath("."+mappingProperties);
            default -> throw new IllegalStateException("Unexpected value: " + targetType);
        }
        salaryDataSchema.setFieldValue(result);
        return salaryDataSchema;
    }
}

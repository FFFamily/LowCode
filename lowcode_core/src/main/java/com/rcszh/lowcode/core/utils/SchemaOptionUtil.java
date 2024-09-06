package com.rcszh.lowcode.core.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.rcszh.lowcode.core.entity.data_source.CoreDataSourceField;
import com.rcszh.lowcode.core.enums.ComponentTypeEnum;
import com.rcszh.lowcode.core.requests.FieldCreateRequest;

import java.util.Arrays;
import java.util.Optional;

public class SchemaOptionUtil {
    public static String getOptions(String type, FieldCreateRequest request) {
        ComponentTypeEnum componentTypeEnum = Arrays.stream(ComponentTypeEnum.values()).filter(item -> item.getType().equals(type)).findFirst().orElse(null);
        if (componentTypeEnum != null){
            JSONObject obj = JSONUtil.createObj();
            Optional.ofNullable(request.getDefaultValue()).ifPresent(value -> obj.set("default", value));
            Optional.ofNullable(request.getReadOnly()).ifPresent(value -> obj.set("readOnly", value));
            switch (componentTypeEnum){
                case INPUT -> {
                    obj.set("x-component", "Input");
                    return obj.toString();
                }
            }
        }
        return null;
    }
}

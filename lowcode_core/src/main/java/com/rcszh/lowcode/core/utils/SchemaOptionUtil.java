//package com.rcszh.lowcode.core.utils;
//
//import cn.hutool.json.JSONObject;
//import cn.hutool.json.JSONUtil;
//import com.rcszh.lowcode.core.enums.InterfaceTypeEnum;
//import com.rcszh.lowcode.core.requests.FieldCreateRequest;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//public class SchemaOptionUtil {
//    public static String getOptions(String type, FieldCreateRequest request) {
//        InterfaceTypeEnum interfaceTypeEnum = Arrays.stream(InterfaceTypeEnum.values()).filter(item -> item.getType().equals(type)).findFirst().orElse(null);
//        if (interfaceTypeEnum != null){
//            JSONObject obj = JSONUtil.createObj();
//            Optional.ofNullable(request.getDefaultValue()).ifPresent(value -> obj.set("default", value));
//            Optional.ofNullable(request.getReadOnly()).ifPresent(value -> obj.set("readOnly", value));
//            switch (interfaceTypeEnum){
//                case INPUT -> {
//                    obj.set("x-component", "Input");
//                    return obj.toString();
//                }
//                case NUMBER -> {
//                    obj.set("x-component", "Input_Number");
//                    return obj.toString();
//                }
//                case RADIO -> {
//                    List<Object> enumValue = request.getEnumValues();
//                    obj.set("x-component", "Radio");
//                    obj.set("enumValue", enumValue);
//                    return obj.toString();
//                }
//            }
//        }
//        return null;
//    }
//}

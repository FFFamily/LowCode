package com.rcszh.lowcode.orm.module;

import com.rcszh.lowcode.orm.SqlFieldConfig;
import com.rcszh.lowcode.orm.entity.FilterCondition;
import com.rcszh.lowcode.orm.enums.SqlFieldTypeEnum;
import jakarta.servlet.FilterConfig;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlUtil {
    /**
     * 替换 SQL
     */
    public static String replaceSQL(String templateSql, HashMap<String,String> map) {
        Pattern TEMPLATE_ARG_PATTERN = Pattern.compile("\\$\\{(.+?)!}"); // ${param!}
        Matcher m = TEMPLATE_ARG_PATTERN.matcher(templateSql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String arg = m.group();
            String replaceStr = map.get(arg);
            m.appendReplacement(sb, replaceStr != null ? replaceStr : "");
        }
        m.appendTail(sb);
        return sb.toString();
    }

    /**
     * 将SQL字段配置转变为真实的SQL语句
     */
    public static String convertFieldConfigToSql(SqlFieldConfig sqlFieldConfig) {
        StringBuilder sb = new StringBuilder();
        Optional.ofNullable(sqlFieldConfig.getFieldName()).ifPresent(value -> sb.append("`").append(value).append("` "));
        Optional.ofNullable(sqlFieldConfig.getFieldType()).ifPresent(value -> sb.append(SqlFieldTypeEnum.getSqlFieldTypeEnum(value).getSqlType()));
        Optional.ofNullable(sqlFieldConfig.getIsNull()).ifPresent(value -> sb.append(value ? " null " : " not null "));
        return sb.toString();
    }

    /**
     * 将过滤配置转为where sql 语句
     */
    public static String convertFilterConfigToSql(FilterCondition filterCondition) {
        return filterCondition.getField() +" " + filterCondition.getOperator() + " " + filterCondition.getValue();
    }


    /**
     * 拼接查询参数
     * @param params 查询参数
     * @return where 部分
     */
    public static String buildQuerySqlByParams(HashMap<String,String> params) {
        if (params == null || params.isEmpty()) {
            return "";
        }
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        int index = 0;
        StringBuffer sb = new StringBuffer();

        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            if (index == 0){
                sb.append(" WHERE ");
                sb.append(entry.getKey());
                sb.append(" = ");
                sb.append(entry.getValue());
            }else {
                sb.append(" AND ");
                sb.append(entry.getKey());
                sb.append(" = ");
                sb.append(entry.getValue());
            }
            index++;
        }
        return sb.toString();
    }
}

package com.rcszh.lowcode.orm.module;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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

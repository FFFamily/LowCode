package com.rcszh.lowcode.orm.params;

import com.rcszh.lowcode.orm.enums.SelectParamTypeEnum;

import java.util.List;

public class SelectWrapperUtil {
    /**
     * 生成 Where 条件
     * @param selectWrapper 条件
     * @return SQL中的where语句
     */
    public static String createWhereParam(SelectWrapper selectWrapper) {
        List<SelectWrapper.SelectParam> queryList = selectWrapper.getQueryList();
        if (queryList == null || queryList.isEmpty()) {
            return "";
        }
        StringBuilder whereBuilder = new StringBuilder();
        whereBuilder.append(" WHERE ");
        for (SelectWrapper.SelectParam selectParam : queryList) {
            String type = selectParam.getType();
            String jdbcType = SelectParamTypeEnum.getByType(type).getJdbcType();
            whereBuilder.append(selectParam.getColumn()).append(jdbcType).append(" ? ");
        }
        return whereBuilder.toString();
    }
}

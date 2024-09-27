package com.rcszh.lowcode.admin.dto.show;

import com.rcszh.lowcode.orm.entity.FilterCondition;
import lombok.Data;

import java.util.List;

@Data
public class FindRequest {
    /**
     * 要查询的表
     */
    private String tableName;
    /**
     * 要查询的字段
     */
    private List<String> tableFiled;
    /**
     * 字段过滤条件
     */
    private List<FilterCondition> filterCondition;
}

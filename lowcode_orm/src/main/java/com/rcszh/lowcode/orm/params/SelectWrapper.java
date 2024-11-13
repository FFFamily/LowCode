package com.rcszh.lowcode.orm.params;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询参数
 */
@Getter
public class SelectWrapper {
    // 参数
    private final List<SelectParam> queryList;

    public SelectWrapper() {
        queryList = new ArrayList<>();
    }

    public SelectWrapper eq(String column, Object value) {
        queryList.add(SelectParam.builder().type("eq").column(column).value(value).build());
        return this;
    }

    @Getter
    @Setter
    @Builder
    public static class SelectParam{
        private String type;
        private String column;
        private Object value;
    }

}

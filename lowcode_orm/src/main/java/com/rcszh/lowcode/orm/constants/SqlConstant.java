package com.rcszh.lowcode.orm.constants;

public class SqlConstant {
    public static final String TABLE_NAME_KEY ="${tableName!}";
    public static final String COLUMN_KEY = "${column!}";
    /**
     * 查询SQL
     */
    public static final String SELECT_SQL = "SELECT "+COLUMN_KEY+" FROM "+TABLE_NAME_KEY;
}

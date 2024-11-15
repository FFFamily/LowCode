package com.rcszh.lowcode.orm.constants;

public class SqlConstant {
    public static final String TABLE_NAME_KEY ="${tableName!}";
    public static final String COLUMN_KEY = "${column!}";
    /**
     * 所有表的ID名称
     */
    public static final String TABLE_ID = "id";
    /**
     * 查询SQL
     */
    public static final String SELECT_SQL = "SELECT "+COLUMN_KEY+" FROM "+TABLE_NAME_KEY;
}

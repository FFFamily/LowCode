package com.rcszh.lowcode.core.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSourceUtil {
    /**
     * 校验数据源有效性
     * TODO 这种校验规则是否可行
     */
    public static boolean validateDataSource(String url, String username, String password) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            connection.close();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("数据源链接错误: "+e.getMessage());
        }
    }
}

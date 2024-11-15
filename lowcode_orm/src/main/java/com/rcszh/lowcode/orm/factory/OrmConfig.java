package com.rcszh.lowcode.orm.factory;

import com.rcszh.lowcode.orm.ORM;
import com.rcszh.lowcode.orm.exceptions.OrmBuilderException;
import lombok.Getter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * ORM 构造器
 */
@Getter
public class OrmConfig {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 查询字段
     */
    private List<String> selectFields;
    /**
     * JDBC
     */
    private JdbcTemplate jdbcTemplate;
    /**
     * 私有构造
     */
    private OrmConfig() {

    }
    /**
     * 构造器
     */
    public static OrmConfig config(){
        return new OrmConfig();
    }
    /**
     * 构建表名
     * @param tableName 表名
     * @return OrmBuilder
     */
    public OrmConfig tableName(String tableName) {
        this.tableName = tableName;
        return this;
    }
    /**
     * 构建JDBC
     * @param jdbcTemplate JDBC
     * @return OrmBuilder
     */
    public OrmConfig jdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        return this;
    }
}

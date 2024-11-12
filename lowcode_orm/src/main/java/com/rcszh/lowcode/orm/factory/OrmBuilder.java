package com.rcszh.lowcode.orm.factory;

import com.rcszh.lowcode.orm.ORM;
import com.rcszh.lowcode.orm.exceptions.OrmBuilderException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;

/**
 * ORM 构造器
 */
@Getter
@Setter
public class OrmBuilder {
    /**
     * 表名
     */
    private String tableName;
    /**
     * JDBC
     */
    private JdbcTemplate jdbcTemplate;

    public ORM create(){
        if (jdbcTemplate == null){
            throw new OrmBuilderException("无法连接数据库");
        }
        return ORM.orm();
    }
}

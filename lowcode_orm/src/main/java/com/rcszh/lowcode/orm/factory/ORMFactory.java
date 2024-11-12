package com.rcszh.lowcode.orm.factory;

import com.rcszh.lowcode.orm.ORM;
import jakarta.annotation.Resource;
import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 构建ORM工厂类
 */
@Component
public class ORMFactory {
    @Resource
    private JdbcTemplate systemJdbcTemplate;


    /**
     * 根据构建器构造ORM
     * @param builder 构造器
     * @return ORM
     */
    public ORM createORM(OrmBuilder builder){
        builder.setJdbcTemplate(checkJDBC(builder.getJdbcTemplate()));
        return builder.create();
    }

    /**
     * 若没有配置JDBC则使用系统默认的
     * @param jdbcTemplate JDBC
     * @return JdbcTemplate
     */
    private JdbcTemplate checkJDBC(JdbcTemplate jdbcTemplate){
        return jdbcTemplate == null ? systemJdbcTemplate : jdbcTemplate;
    }


}

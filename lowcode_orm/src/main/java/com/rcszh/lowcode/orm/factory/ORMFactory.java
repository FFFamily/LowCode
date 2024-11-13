package com.rcszh.lowcode.orm.factory;

import com.rcszh.lowcode.orm.ORM;
import jakarta.annotation.Resource;
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
     * @param config ORM 配置
     * @return ORM
     */
    public ORM createORM(OrmConfig config){
        config.jdbcTemplate(checkJDBC(config.getJdbcTemplate()));
        return new ORM(config);
    }
    /**
     * 默认构造 ORM
     */
    public ORM createORM(){
        return createORM(OrmConfig.config());
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

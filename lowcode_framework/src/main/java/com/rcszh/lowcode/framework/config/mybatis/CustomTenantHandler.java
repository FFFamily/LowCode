package com.rcszh.lowcode.framework.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.rcszh.lowcode.framework.module.TenantContextHolder;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.stereotype.Component;

/**
 * Mybatis-Plus 多租户处理器
 * 多租户不等于权限过滤，租户之间是完全隔离的。
 * 启用多租户后，所有执行的 method 的 SQL 都会进行处理。
 * 自定义的 SQL 请按规范书写，特别是涉及到多个表的每个表都要给别名，特别是 inner join 的要写标准的 inner join
 */
@Component
public class CustomTenantHandler implements TenantLineHandler {
    @Override
    public Expression getTenantId() {
        // 假设有一个租户上下文，能够从中获取当前用户的租户
        Long tenantCode = TenantContextHolder.getCurrentTenantCode();
//        return new StringValue(tenantCode);
        return new LongValue(tenantCode);
    }

    @Override
    public String getTenantIdColumn() {
        return "tenant_id";
    }

    @Override
    public boolean ignoreTable(String tableName) {
        // 根据需要返回是否忽略该表
        return false;
    }

}

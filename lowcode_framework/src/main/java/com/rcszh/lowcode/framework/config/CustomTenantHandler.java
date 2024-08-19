package com.rcszh.lowcode.framework.config;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import net.sf.jsqlparser.expression.Expression;
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
//        // 假设有一个租户上下文，能够从中获取当前用户的租户
//        Long tenantId = TenantContextHolder.getCurrentTenantId();
//        // 返回租户ID的表达式，LongValue 是 JSQLParser 中表示 bigint 类型的 class
//        return new LongValue(tenantId);;
        return null;
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

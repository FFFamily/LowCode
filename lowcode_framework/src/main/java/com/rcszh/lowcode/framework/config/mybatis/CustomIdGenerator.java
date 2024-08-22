package com.rcszh.lowcode.framework.config.mybatis;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.rcszh.lowcode.framework.config.SnowIdConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义Id生成器
 */
@Component
public class CustomIdGenerator implements IdentifierGenerator {
    @Autowired
    private SnowIdConfig snowIdConfig;

    /**
     * 针对类型为：ASSIGN_ID
     */
    @Override
    public Number nextId(Object entity) {
        return snowIdConfig.getSnowId();
    }
}

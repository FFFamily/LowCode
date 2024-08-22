package com.rcszh.lowcode.framework.config;

import com.rcszh.lowcode.common.utils.SnowflakeIdUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取雪花ID相关配置
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "snowid")
public class SnowIdConfig {


    /**
     * 获取雪花ID
     *
     * @return long
     */
    public long getSnowId() {
        return SnowflakeIdUtils.getInstance(this.workerId, this.datacenterId).nextId();
    }

    /**
     * 工作机器ID(0~31)
     */
    private long workerId;

    /**
     * 数据中心ID(0~31)
     */
    private long datacenterId;

}

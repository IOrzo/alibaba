package com.sixtofly.apple.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author xie yuan bing
 * @date 2019-06-15 15:52
 * @description
 */
@Component
@RefreshScope
@ConfigurationProperties(prefix = "nacos.config")
@Data
public class NacosConfig {

    private String app;

    private String version;
}

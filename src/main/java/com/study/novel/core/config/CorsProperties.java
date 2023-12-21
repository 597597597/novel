package com.study.novel.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 配置跨域属性
 * @author 597
 */

@ConfigurationProperties(prefix = "novel.cors")
@Data
@Component
public class CorsProperties {
    /**
     * 允许跨域的域名
     */
    private List<String> allowOrigins;
}

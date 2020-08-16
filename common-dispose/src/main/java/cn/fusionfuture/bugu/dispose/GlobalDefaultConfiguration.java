package cn.fusionfuture.bugu.dispose;

import cn.fusionfuture.bugu.dispose.exception.GlobalExceptionHandler;
import cn.fusionfuture.bugu.dispose.response.GlobalResponseHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author thomas
 * @version 1.0
 * @class GlobalDefaultConfiguration
 * @description 配置类；主要用于配置两个handler
 * @date 2020/8/16 2:02 下午
 */
@Configuration
@EnableConfigurationProperties(GlobalResponseProperties.class)
@PropertySource(value = "classpath:dispose.properties", encoding = "UTF-8")
public class GlobalDefaultConfiguration {
    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    public GlobalResponseHandler globalResponseHandler(GlobalResponseProperties globalDefaultProperties) {
        return new GlobalResponseHandler(globalDefaultProperties);
    }
}

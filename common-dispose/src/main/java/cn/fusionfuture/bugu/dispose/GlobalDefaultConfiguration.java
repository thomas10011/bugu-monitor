package cn.fusionfuture.bugu.dispose;

import cn.fusionfuture.bugu.dispose.exception.GlobalExceptionHandler;
import cn.fusionfuture.bugu.dispose.fill.CustomMetaObjectHandler;
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

    /**
     * @author thomas
     * @description  将GlobalExceptionHandler注入IOC容器 用于统一处理异常
     * @create 2020/8/17 7:26 下午
     * @update 2020/8/17 7:26 下午
     * @return cn.fusionfuture.bugu.dispose.exception.GlobalExceptionHandler
     **/
    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    /**
     * @author thomas
     * @description 将GlobalResponseHandler注入IOC容器 用于封装统一返回结果
     * @create 2020/8/17 7:26 下午
     * @update 2020/8/17 7:26 下午
     * @param globalDefaultProperties 全局配置文件
     * @return cn.fusionfuture.bugu.dispose.response.GlobalResponseHandler
     **/
    @Bean
    public GlobalResponseHandler globalResponseHandler(GlobalResponseProperties globalDefaultProperties) {
        return new GlobalResponseHandler(globalDefaultProperties);
    }

    /**
     * @author thomas
     * @description 将CustomMetaObjectHandler注入IOC容器 用于对字段进行自动填充
     * @create 2020/8/17 7:27 下午
     * @update 2020/8/17 7:27 下午
     * @return cn.fusionfuture.bugu.dispose.fill.CustomMetaObjectHandler
     **/
    @Bean
    public CustomMetaObjectHandler customMetaObjectHandler() {
        return new CustomMetaObjectHandler();
    }

}

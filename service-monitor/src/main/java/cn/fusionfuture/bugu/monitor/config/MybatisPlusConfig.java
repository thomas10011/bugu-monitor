package cn.fusionfuture.bugu.monitor.config;

import cn.fusionfuture.bugu.dispose.fill.CustomMetaObjectHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author thomas
 * @version 1.0
 * @class MybatisPlusConfig
 * @description 配置mybatis plus
 * @date 2020/9/26 2:21 下午
 */
@Configuration
public class MybatisPlusConfig {
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

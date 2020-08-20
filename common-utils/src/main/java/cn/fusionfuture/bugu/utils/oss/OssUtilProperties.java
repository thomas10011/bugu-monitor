package cn.fusionfuture.bugu.utils.oss;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author thomas
 * @version 1.0
 * @class OssConfig
 * @description 用于动态从yml配置文件中获取bucket和endpoint
 * @date 2020/8/19 2:34 下午
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.cloud.alicloud.oss")
@PropertySource(value = "classpath:application.yml", encoding = "UTF-8")
public class OssUtilProperties {

    private String endpoint;

    private String bucket;

    @Value("${calllback-url}")
    private String callbackUrl;

}

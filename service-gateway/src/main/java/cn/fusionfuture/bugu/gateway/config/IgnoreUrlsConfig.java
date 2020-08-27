package cn.fusionfuture.bugu.gateway.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author thomas
 * @version 1.0
 * @class IgoreUrlsConfig
 * @description 读取配置文件中的白名单URI
 * @date 2020/8/22 6:33 下午
 */
@Data
@Component
@ConfigurationProperties(prefix="secure.ignore")
public class IgnoreUrlsConfig {

    private List<String> urls;

}

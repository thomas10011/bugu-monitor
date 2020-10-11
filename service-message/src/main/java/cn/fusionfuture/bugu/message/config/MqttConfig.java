package cn.fusionfuture.bugu.message.config;

/**
 * @author DELL
 * @version 1.0
 * @class MqttConfig
 * @description TODO
 * @date 2020/9/26 10:02
 */
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.stereotype.Component;

/**
 * @Author: xinzhifu
 * @Description: 基础配置类
 * @date 2020/6/8 18:25
 */
@Getter
@Setter
@Component
@IntegrationComponentScan
@ConfigurationProperties(prefix = "mqtt")
public class MqttConfig {

    /**
     * 服务地址
     */
    @Value("${mqtt.servers:#{null}}")
    private String[] servers;

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 服务端id
     */
    private String serverClientId;

    /**
     * 默认主题
     */
    private String defaultTopic;

//    用户名

    private  String username;

//    密码
    private  String password;

//    心跳时间
    private Integer KeepAliveInterval;
//    是否自动重连
    private Boolean AutomaticReconnect;
//    是否保持session
    private Boolean CleanSession;
}

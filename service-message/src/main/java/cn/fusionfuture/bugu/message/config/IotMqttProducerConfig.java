package cn.fusionfuture.bugu.message.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * @author DELL
 * @version 1.0
 * @class IotMqttProducerConfig
 * @description 消息发送的设置
 * @date 2020/9/26 9:48
 */
@Configuration
public class IotMqttProducerConfig {

    @Autowired
    private MqttConfig mqttConfig;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();//连接参数
        options.setServerURIs(mqttConfig.getServers());//连接地址
        if(null!=mqttConfig.getUsername()) {
            options.setUserName(mqttConfig.getUsername());//用户名
        }
        if(null!=mqttConfig.getPassword()) {
            options.setPassword(mqttConfig.getPassword().toCharArray());//密码
        }
        options.setKeepAliveInterval(mqttConfig.getKeepAliveInterval());//心跳时间
        options.setAutomaticReconnect(mqttConfig.getAutomaticReconnect());//断开是否自动重联
        options.setCleanSession(mqttConfig.getCleanSession());//保持session
        factory.setConnectionOptions(options);
//        factory.setServerURIs(mqttConfig.getServers());
        return factory;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "iotMqttInputChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(mqttConfig.getServerClientId(), mqttClientFactory());
        messageHandler.setAsync(false);
        messageHandler.setDefaultTopic(mqttConfig.getDefaultTopic());
        return messageHandler;
    }
}

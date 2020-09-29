package cn.fusionfuture.bugu.message.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * @author DELL
 * @version 1.0
 * @class MmsRabbitMQGatewayService
 * @description TODO
 * @date 2020/9/27 15:25
 **/

@MessagingGateway(defaultRequestChannel = "iotMqttInputChannel")
public interface IMmsRabbitMQGatewayService {

    void sendMessage2Mqtt(String data);

    void sendMessage2Mqtt(String data, @Header(MqttHeaders.TOPIC) String topic);

    void sendMessage2Mqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);
}
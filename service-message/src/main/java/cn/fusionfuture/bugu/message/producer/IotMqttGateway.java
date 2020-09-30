package cn.fusionfuture.bugu.message.producer;

/**
 * @author DELL
 * @version 1.0
 * @class IotMqttGateway
 * @description rabbitmq mqtt协议网关接口
 * @date 2020/9/26 10:05
 */
import cn.fusionfuture.bugu.pojo.entity.MmsPrivateChat;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "iotMqttInputChannel")
public interface IotMqttGateway {

    void sendMessage2Mqtt(String data);

    void sendMessage2Mqtt(String data, @Header(MqttHeaders.TOPIC) String topic);

    void sendMessage2Mqtt(MmsPrivateChat data, @Header(MqttHeaders.TOPIC) String topic);

    void sendMessage2Mqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);
}

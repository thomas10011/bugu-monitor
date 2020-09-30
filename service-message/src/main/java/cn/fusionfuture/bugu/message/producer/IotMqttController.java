package cn.fusionfuture.bugu.message.producer;

/**
 * @author DELL
 * @version 1.0
 * @class IotMqttController
 * @description
 * @date 2020/9/26 10:06
 */
import cn.fusionfuture.bugu.message.service.IMmsRabbitMQGatewayService;
import cn.fusionfuture.bugu.pojo.entity.MmsPrivateChat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fun")
public class IotMqttController {

    @Autowired
    private IMmsRabbitMQGatewayService iMmsRabbitMQGatewayService;

    @GetMapping("/sendMessage")
    @ResponseBody
    public String sendMqtt(@RequestParam(value = "topic") String topic, @RequestParam(value = "message") String message) {
        iMmsRabbitMQGatewayService.sendMessage2Mqtt(message, topic);
        return "SUCCESS";
    }

    @PostMapping("/sendPrivateMessage")
    @ResponseBody
    public String sendPrivateMessage(@RequestParam(value = "topic") String topic,  MmsPrivateChat mmsPrivateChat) throws JsonProcessingException {
//        String message = mmsPrivateChat.getMessageContent();
        ObjectMapper mapper = new ObjectMapper();
        String messaged = mapper.writeValueAsString(mmsPrivateChat);
        iMmsRabbitMQGatewayService.sendMessage2Mqtt(messaged, mmsPrivateChat.getReceiveUserId()+"");
        return "SUCCESS";
    }

}

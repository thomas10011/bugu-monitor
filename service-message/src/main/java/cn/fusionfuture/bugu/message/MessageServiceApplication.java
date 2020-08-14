package cn.fusionfuture.bugu.message;

import cn.fusionfuture.bugu.pojo.api.CommonResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

/**
 * @class MessageServiceApplication
 * @description 消息微服务祝启动类
 * @author thomas
 * @date 2020/8/14 12:51 上午
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MessageServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageServiceApplication.class, args);
    }
}
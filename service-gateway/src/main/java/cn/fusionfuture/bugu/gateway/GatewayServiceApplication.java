package cn.fusionfuture.bugu.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @class GatewayServiceApplication
 * @description 网关主启动类
 * @author thomas
 * @date 2020/8/15 2:56 下午
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
}
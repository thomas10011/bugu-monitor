package cn.fusionfuture.bugu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName UserServiceApplication
 * @Description 用户微服务祝启动类
 * @Author thomas
 * @Date 2020/8/14 12:58 上午
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
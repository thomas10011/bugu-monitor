package cn.fusionfuture.bugu.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName MonitorServiceApplication
 * @Description 监督计划微服务祝启动类
 * @Author thomas
 * @Date 2020/8/14 12:55 上午
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MonitorServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitorServiceApplication.class, args);
    }
}
package cn.fusionfuture.bugu.pk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName PkServiceApplication
 * @Description pk计划微服务祝启动类
 * @Author thomas
 * @Date 2020/8/14 12:56 上午
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PkServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PkServiceApplication.class, args);
    }
}
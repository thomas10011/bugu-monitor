package cn.fusionfuture.bugu.pk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @class PkServiceApplication
 * @description pk计划微服务祝启动类
 * @author thomas
 * @date 2020/8/14 12:56 上午
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("cn.fusionfuture.bugu.pk.mapper")
public class PkServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PkServiceApplication.class, args);
    }
}
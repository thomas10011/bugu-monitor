package cn.fusionfuture.bugu.monitor;

import cn.fusionfuture.bugu.dispose.annotation.EnableGlobalDispose;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @class MonitorServiceApplication
 * @description 监督计划微服务祝启动类
 * @author thomas
 * @date 2020/8/14 12:55 上午
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableGlobalDispose
@EnableScheduling
@MapperScan("cn.fusionfuture.bugu.monitor.mapper")
public class MonitorServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitorServiceApplication.class, args);
    }
}
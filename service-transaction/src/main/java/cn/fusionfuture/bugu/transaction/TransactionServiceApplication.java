package cn.fusionfuture.bugu.transaction;

import cn.fusionfuture.bugu.dispose.annotation.EnableGlobalDispose;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @class TransactionServiceApplication
 * @description 交易微服务祝启动类
 * @author thomas
 * @date 2020/8/14 12:58 上午
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableGlobalDispose
@MapperScan("cn.fusionfuture.bugu.transaction.mapper")
public class TransactionServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionServiceApplication.class, args);
    }
}
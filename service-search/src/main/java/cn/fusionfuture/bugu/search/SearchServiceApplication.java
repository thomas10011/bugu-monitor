package cn.fusionfuture.bugu.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author thomas
 * @version 1.0
 * @class SearchServiceApplication
 * @description 搜索微服务主启动类
 * @date 2020/9/19 11:30 下午
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SearchServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchServiceApplication.class, args);
    }
}
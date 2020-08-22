package cn.fusionfuture.bugu.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
  *@class cn.fusionfuture.bugu.oauth.OauthServiceApplication
  *@description 鉴权模块主启动类
  *@author thomas
  *@date 2020/8/21 10:11 下午
  *@version 1.0
  */
@SpringBootApplication
public class OauthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OauthServiceApplication.class,args);
    }
}
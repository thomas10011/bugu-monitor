package cn.fusionfuture.bugu.message.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author thomas
 * @version 1.0
 * @class UtilsConfig
 * @description 扫描工具里所在包
 * @date 2020/8/19 4:29 下午
 */
@Configuration
@ComponentScan(value = "cn.fusionfuture.bugu.utils.oss")
public class UtilsConfig {

}

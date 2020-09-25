package cn.fusionfuture.bugu.monitor.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author thomas
 * @version 1.0
 * @class UtilsConfig
 * @description 配置需要用到的工具类
 * @date 2020/8/28 9:38 下午
 */
@Configuration
@ComponentScan(value = "cn.fusionfuture.bugu.utils.oss")
public class UtilsConfig {
}

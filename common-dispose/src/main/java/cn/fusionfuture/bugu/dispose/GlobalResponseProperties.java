package cn.fusionfuture.bugu.dispose;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thomas
 * @version 1.0
 * @class GlobalDefaultProperties
 * @description 用于配置GlobalResponseHandler
 * @date 2020/8/16 2:37 下午
 */
@Data
@ConfigurationProperties(GlobalResponseProperties.PREFIX)
public class GlobalResponseProperties {

    public static final String PREFIX = "dispose";

    /**
     * 统一返回过滤包
     */
    private List<String> handlerFilterPackage = new ArrayList<>();

    /**
     * 统一返回过滤类
     */
    private List<String> handlerFilterClass = new ArrayList<>();

    /**
     * 所在微服务的名称
     */
    private String serviceName;

}

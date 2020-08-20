package cn.fusionfuture.bugu.message.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author thomas
 * @description feign调用pk-service
 * @create 2020/8/20 3:26 下午
 * @update 2020/8/20 3:26 下午
 **/
@FeignClient(value = "pk-service")
public interface PkFeignService {
    /**
     * @author thomas
     * @description 远程调用pk-service的getTest方法
     * @create 2020/8/20 3:31 下午
     * @update 2020/8/20 3:31 下午
     * @return java.lang.String
     **/
    @GetMapping(value = "/feign")
    String getTest();

}

package cn.fusionfuture.bugu.pk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thomas
 * @version 1.0
 * @class TestController
 * @description 测试feign调用
 * @date 2020/8/20 3:27 下午
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value = "/feign")
    String getTest() {
        return "feign result";
    }
}

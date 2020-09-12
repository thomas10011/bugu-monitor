package cn.fusionfuture.bugu.message;


import cn.fusionfuture.bugu.dispose.annotation.EnableIgnoreResponse;
import cn.fusionfuture.bugu.message.feign.PkFeignService;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.api.ResultCode;
import cn.fusionfuture.bugu.pojo.entity.UmsUserFollow;
import cn.fusionfuture.bugu.utils.oss.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

/**
 * @author thomas
 * @version 1.0
 * @class TestController
 * @description TODO
 * @date 2020/8/15 11:35 下午
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    PkFeignService pkFeignService;

    @GetMapping(value = "/hello")
    @EnableIgnoreResponse
    public String test() {
        return pkFeignService.getTest();
    };

    @GetMapping(value = "/hello2")
    public Map<String, String> getMap() throws ServletException, IOException {
        return OssUtil.getPolicy("user-dir-prefix/");
    }

    @GetMapping(value = "/hello3")
    public ResultCode getCode() {
        return ResultCode.METHOD_NOT_ALLOWED;
    }

    @GetMapping(value = "/hello4")
    public UmsUserFollow getTest() {
        return new UmsUserFollow()
                .setId(1L)
                .setUserId(2L)
                .setFollowedUserId(3L);
    }

    @GetMapping(value = "/hello5")
    public CommonResult getTest2() {
        return CommonResult.success().append(
                new UmsUserFollow()
                        .setId(1L)
                        .setUserId(2L)
                        .setFollowedUserId(3L)
        );
    }

//    @GetMapping(value = "expcetion")
//    public String exception() {
//        throw new BusinessException(400, "业务异常错误信息");
//    }
}

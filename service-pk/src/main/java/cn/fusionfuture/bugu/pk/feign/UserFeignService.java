package cn.fusionfuture.bugu.pk.feign;

import cn.fusionfuture.bugu.pojo.api.CommonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashMap;

@FeignClient(value = "user-service")
public interface UserFeignService {

    @GetMapping("/get-details-for-message")
    @Valid
    @ApiOperation(value = "远程调用获取用户名和头像")
    CommonResult<HashMap<String,String>> getDetailsForMessage(@ApiParam(value = "用户id") @RequestParam(value = "id") Long id);
}

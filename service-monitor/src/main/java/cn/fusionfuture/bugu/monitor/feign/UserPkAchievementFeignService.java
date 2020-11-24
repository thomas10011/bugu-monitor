package cn.fusionfuture.bugu.monitor.feign;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-service")
public interface UserPkAchievementFeignService {

    @GetMapping(value = "/achievement/pk-plan/plan-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户的pk计划的总数，总数+1")
    void updatePlanCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                         @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

    @GetMapping(value = "/achievement/pk-plan/success-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户pk计划的成功次数，总数+1")
    void updateSuccessCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                            @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

    @GetMapping(value = "/achievement/pk-plan/defeat-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户战胜的人数，总数+1")
    void updateDefeatCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                           @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

    @GetMapping(value = "/achievement/pk-plan/victory-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户胜利次数，总数+1")
    void updateVictoryCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                            @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

}

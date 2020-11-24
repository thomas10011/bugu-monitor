package cn.fusionfuture.bugu.pk.feign;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-service")
public interface UserMonitorAchievementFeignService {
    @GetMapping(value = "/achievement/monitor-plan/plan-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户的监督计划的总数，总数+1")
    void updatePlanCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                         @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

    @GetMapping(value = "/achievement/monitor-plan/success-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户的监督计划成功次数，总数+1")
    void updateSuccessCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                            @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

    @GetMapping(value = "/achievement/monitor-plan/participate-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户的监督计划的参与人数，总数+1")
    void updateParticipateCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                                @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);
}


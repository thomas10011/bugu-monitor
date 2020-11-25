package cn.fusionfuture.bugu.monitor.feign;

import cn.fusionfuture.bugu.pojo.api.CommonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashMap;
@FeignClient(value = "user-service")
public interface UserFeignService {

    @GetMapping("/get-details-for-message")
    @Valid
    @ApiOperation(value = "远程调用获取用户名和头像")
    CommonResult<HashMap<String,String>> getDetailsForMessage(@ApiParam(value = "用户id") @RequestParam(value = "id") Long id);

    @GetMapping(value = "/achievement/monitor/plan-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户监督的计划数，总数+1")
    void updatePlanCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                         @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

    @GetMapping(value = "/achievement/monitor/success-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户监督的成功的计划数 总数+1" )
    void updateSuccessCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                            @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

    @GetMapping(value = "/achievement/monitor/vote-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户监督的计划的投票总数 总数+1" )
    void updateVoteCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                         @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

    @GetMapping(value = "/achievement/monitor/assist-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户助力人数 总数+1" )
    void updateAssistCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                           @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

    @GetMapping(value = "/achievement/monitor-plan/plan-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户的监督计划的总数，总数+1")
    void updateMonitorPlanCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                         @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

    @GetMapping(value = "/achievement/monitor-plan/success-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户的监督计划成功次数，总数+1")
    void updateMonitorSuccessCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                            @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

    @GetMapping(value = "/achievement/monitor-plan/participate-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户的监督计划的参与人数，总数+1")
    void updateMonitorParticipateCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                                @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

}

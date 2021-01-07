package cn.fusionfuture.bugu.pk.feign;

import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.entity.UmsMonitorAchievement;
import cn.fusionfuture.bugu.pojo.entity.UmsMonitorPlanAchievement;
import cn.fusionfuture.bugu.pojo.entity.UmsPkPlanAchievement;
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

    @GetMapping(value = "/user/achievement/monitor/plan-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户监督的计划数，总数+1")
    void updatePlanCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                         @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

    @GetMapping(value = "/user/achievement/monitor/success-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户监督的成功的计划数 总数+1" )
    void updateSuccessCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                            @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

    @GetMapping(value = "/user/achievement/monitor/vote-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户监督的计划的投票总数 总数+1" )
    void updateVoteCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                         @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

    @GetMapping(value = "/user/achievement/monitor/assist-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户助力人数 总数+1" )
    void updateAssistCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                           @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

    @GetMapping(value = "/user/achievement/pk-plan/plan-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户的pk计划的总数，总数+1")
    void updatePkPlanCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                         @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

    @GetMapping(value = "/user/achievement/pk-plan/success-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户pk计划的成功次数，总数+1")
    void updatePkSuccessCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                            @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

    @GetMapping(value = "/user/achievement/pk-plan/defeat-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户战胜的人数，总数+1")
    void updateDefeatCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                           @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

    @GetMapping(value = "/user/achievement/pk-plan/victory-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户胜利次数，总数+1")
    void updateVictoryCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                            @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt);

}

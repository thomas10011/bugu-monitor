package cn.fusionfuture.bugu.user.controller;


import cn.fusionfuture.bugu.pojo.entity.UmsMonitorAchievement;
import cn.fusionfuture.bugu.pojo.entity.UmsUserFollow;
import cn.fusionfuture.bugu.user.service.IUmsMonitorAchievementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  查询用户的个人成就
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户监督的计划的相关成就")
public class UmsMonitorAchievementController {

    @Autowired
    IUmsMonitorAchievementService monitorAchievementService;

    @GetMapping(value = "/achievement/monitor/plan-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户监督的计划数，总数+1")
    void updatePlanCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid) {
        UmsMonitorAchievement achievement = monitorAchievementService.getById(uid);
        monitorAchievementService.updateById(
                new UmsMonitorAchievement()
                        .setUserId(uid)
                        .setPlanCount(achievement.getPlanCount() + 1));
    }

    @GetMapping(value = "/achievement/monitor/success-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户监督的成功的计划数 总数+1" )
    void updateSuccessCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid) {
        UmsMonitorAchievement achievement = monitorAchievementService.getById(uid);
        monitorAchievementService.updateById(
                new UmsMonitorAchievement()
                        .setUserId(uid)
                        .setSuccessCount(achievement.getSuccessCount() + 1));
    }

    @GetMapping(value = "/achievement/monitor/vote-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户监督的计划的投票总数 总数+1" )
    void updateVoteCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid) {
        UmsMonitorAchievement achievement = monitorAchievementService.getById(uid);
        monitorAchievementService.updateById(
                new UmsMonitorAchievement()
                        .setUserId(uid)
                        .setVoteCount(achievement.getVoteCount() + 1));
    }

    @GetMapping(value = "/achievement/monitor/assist-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户助力人数 总数+1" )
    void updateAssistCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid) {
        UmsMonitorAchievement achievement = monitorAchievementService.getById(uid);
        monitorAchievementService.updateById(
                new UmsMonitorAchievement()
                        .setUserId(uid)
                        .setAssistCount(achievement.getAssistCount() + 1));
    }

}

package cn.fusionfuture.bugu.user.controller;


import cn.fusionfuture.bugu.pojo.entity.UmsMonitorPlanAchievement;
import cn.fusionfuture.bugu.user.service.IUmsMonitorPlanAchievementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户的监督计划相关成就")
public class UmsMonitorPlanAchievementController {

    @Autowired
    IUmsMonitorPlanAchievementService achievementService;

    @GetMapping(value = "/achievement/monitor-plan/plan-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户的监督计划的总数，总数+1")
    void updateMonitorPlanCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                                @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt) {
        UmsMonitorPlanAchievement achievement = achievementService.getById(uid);
        achievementService.updateById(
                new UmsMonitorPlanAchievement()
                        .setUserId(uid)
                        .setPlanCount(achievement.getPlanCount() + amt));
    }

    @GetMapping(value = "/achievement/monitor-plan/success-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户的监督计划成功次数，总数+1")
    void updateMonitorSuccessCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                                   @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt) {
        UmsMonitorPlanAchievement achievement = achievementService.getById(uid);
        achievementService.updateById(
                new UmsMonitorPlanAchievement()
                        .setUserId(uid)
                        .setSuccessCount(achievement.getSuccessCount() + amt));
    }

    @GetMapping(value = "/achievement/monitor-plan/participate-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户的监督计划的参与人数，总数+1")
    void updateMonitorParticipateCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                                       @ApiParam(value = "增加或者减少的总数，默认为1") @RequestParam(value = "amt", defaultValue = "1") Integer amt) {
        UmsMonitorPlanAchievement achievement = achievementService.getById(uid);
        achievementService.updateById(
                new UmsMonitorPlanAchievement()
                        .setUserId(uid)
                        .setParticipateCount(achievement.getParticipateCount() + amt));
    }


}

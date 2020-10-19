package cn.fusionfuture.bugu.user.controller;


import cn.fusionfuture.bugu.pojo.entity.UmsMonitorAchievement;
import cn.fusionfuture.bugu.pojo.entity.UmsPkPlanAchievement;
import cn.fusionfuture.bugu.user.service.IUmsMonitorAchievementService;
import cn.fusionfuture.bugu.user.service.IUmsPkPlanAchievementService;
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
@Api(tags = "用户的pk计划相关成就")
public class UmsPkPlanAchievementController {

    @Autowired
    IUmsPkPlanAchievementService pkPlanAchievementService;

    @GetMapping(value = "/achievement/pk-plan/plan-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户的pk计划的总数，总数+1")
    void updatePlanCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid) {
        UmsPkPlanAchievement achievement = pkPlanAchievementService.getById(uid);
        pkPlanAchievementService.updateById(
                new UmsPkPlanAchievement()
                        .setUserId(uid)
                        .setPlanCount(achievement.getPlanCount() + 1));
    }

    @GetMapping(value = "/achievement/pk-plan/success-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户pk计划的成功次数，总数+1")
    void updateSuccessCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid) {
        UmsPkPlanAchievement achievement = pkPlanAchievementService.getById(uid);
        pkPlanAchievementService.updateById(
                new UmsPkPlanAchievement()
                        .setUserId(uid)
                        .setSuccessCount(achievement.getSuccessCount() + 1));
    }

    @GetMapping(value = "/achievement/pk-plan/defeat-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户战胜的人数，总数+1")
    void updateDefeatCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid) {
        UmsPkPlanAchievement achievement = pkPlanAchievementService.getById(uid);
        pkPlanAchievementService.updateById(
                new UmsPkPlanAchievement()
                        .setUserId(uid)
                        .setDefeatCount(achievement.getDefeatCount() + 1));
    }

    @GetMapping(value = "/achievement/pk-plan/victory-count/{uid}")
    @ApiOperation(value = "供计划微服务远程调用 更新用户胜利次数，总数+1")
    void updateVictoryCount(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid) {
        UmsPkPlanAchievement achievement = pkPlanAchievementService.getById(uid);
        pkPlanAchievementService.updateById(
                new UmsPkPlanAchievement()
                        .setUserId(uid)
                        .setVictoryCount(achievement.getVictoryCount() + 1));
    }

}

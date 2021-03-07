package cn.fusionfuture.bugu.user.controller;

import cn.fusionfuture.bugu.pojo.entity.UmsMonitorAchievement;
import cn.fusionfuture.bugu.pojo.entity.UmsMonitorPlanAchievement;
import cn.fusionfuture.bugu.pojo.entity.UmsPkPlanAchievement;
import cn.fusionfuture.bugu.user.service.IUmsMonitorAchievementService;
import cn.fusionfuture.bugu.user.service.IUmsMonitorPlanAchievementService;
import cn.fusionfuture.bugu.user.service.IUmsPkPlanAchievementService;
import cn.fusionfuture.bugu.user.vo.UserAchievementVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thomas
 * @version 1.0
 * @class UmsUserAchievementVO
 * @description TODO
 * @date 2020/10/19 8:59 上午
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户成就相关接口")
public class UmsUserAchievement {

    @Autowired
    IUmsMonitorAchievementService monitorAchievementService;

    @Autowired
    IUmsPkPlanAchievementService pkPlanAchievementService;

    @Autowired
    IUmsMonitorPlanAchievementService monitorPlanAchievementService;

    @GetMapping(value = "/achievement/{uid}")
    @ApiOperation(value = "根据用户id查询用户的成就")
    UserAchievementVO queryUserAchievement(@ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid) {
        UmsPkPlanAchievement pkPlanAchievement = pkPlanAchievementService.getById(uid);
        UmsMonitorPlanAchievement monitorPlanAchievement = monitorPlanAchievementService.getById(uid);
        UmsMonitorAchievement monitorAchievement = monitorAchievementService.getById(uid);

        return new UserAchievementVO()
                .setIac(monitorAchievement.getAssistCount())
                .setIpc(monitorAchievement.getPlanCount())
                .setIpsc(monitorAchievement.getSuccessCount())
                .setIvc(monitorAchievement.getSuccessCount())
                .setMpc(monitorPlanAchievement.getPlanCount())
                .setMppc(monitorPlanAchievement.getParticipateCount())
                .setMpsc(monitorPlanAchievement.getSuccessCount())
                .setPpc(pkPlanAchievement.getPlanCount())
                .setPpdc(pkPlanAchievement.getDefeatCount())
                .setPpsc(pkPlanAchievement.getSuccessCount())
                .setPpvc(pkPlanAchievement.getVictoryCount());
    }




}

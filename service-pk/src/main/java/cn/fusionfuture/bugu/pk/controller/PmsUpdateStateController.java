package cn.fusionfuture.bugu.pk.controller;

import cn.fusionfuture.bugu.pk.service.IPmsUpdateStateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zws
 * @version 1.0
 * @class PmsUpdateStateController
 * @description 用于检测打卡以及计划结果并更新
 * @date 2020/11/11 21:53
 */
@RestController
@Api(tags = "检测器")
public class PmsUpdateStateController {
    @Autowired
    IPmsUpdateStateService updateStateService;

    @GetMapping("/check/plan-is-start/{uid}")
    @ApiOperation(value = "根据用户id检查用户创建的计划是否开始并更新")
    public void checkPlanIsStart(@ApiParam(value = "用户的id") @PathVariable Long uid){
        updateStateService.checkPlanIsStart(uid);
    }

    @GetMapping("/check/plan-is-end/{uid}")
    @ApiOperation(value = "根据用户id检查用户创建的计划是否结束并更新")
    public void checkPlanIsEnd(@ApiParam(value = "用户的id") @PathVariable Long uid){
        updateStateService.checkPlanIsEnd(uid);
    }

    @GetMapping("/check/judge-plan-result/{uid}")
    @ApiOperation(value = "根据用户id检查用户参与计划的结果并更新")
    public void judgePlanResult(@ApiParam(value = "用户的id") @PathVariable Long uid){
        updateStateService.judgePlanResult(uid);
    }

    @GetMapping("/check/judge-punch-result/{uid}")
    @ApiOperation(value = "根据用户id检查用户打卡的结果并更新")
    public void judgePunchResult(@ApiParam(value = "用户的id") @PathVariable Long uid){
        updateStateService.judgePunchResult(uid);
    }


}
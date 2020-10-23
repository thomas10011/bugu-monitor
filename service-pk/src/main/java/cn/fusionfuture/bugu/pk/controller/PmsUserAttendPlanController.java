package cn.fusionfuture.bugu.pk.controller;


import cn.fusionfuture.bugu.pk.service.IPmsUserAttendPlanService;
import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
@RestController
@Api(tags = "参与计划的记录" )
public class PmsUserAttendPlanController {

    @Autowired
    IPmsUserAttendPlanService userAttendPlanService;

    @GetMapping(value = "/pk-plan/attend-plan/{uid}")
    @ApiOperation(value = "根据用户id查询用户参与的pk计划")
    PageInfo <BasicPkPlanVO> queryPkUserAttendPlanByUserId(@ApiParam(value = "用户的id") @PathVariable Long uid,
                                                           @ApiParam(value = "查询的页码") @RequestParam(name = "pn") Integer pn,
                                                           @ApiParam(value = "查询的页面大小") @RequestParam(name = "ps") Integer ps) {
        return userAttendPlanService.queryPkUserAttendPlanByUserId(pn,ps,uid);
    }

    @PostMapping(value = "/attend")
    @ApiOperation(value = "用户参与pk计划")
    public Long punch(@ApiParam(value = "用户id") @RequestParam Long userId,
                      @ApiParam(value = "计划id") @RequestParam Long planId) {
        return userAttendPlanService.userAttendPlan(userId,planId);
    }

//    @GetMapping(value = "/pk-plan/pk/{pid}")
//    BasicPkPlanVO queryPkUserAttendPlanByPlanId(@PathVariable Long pid) {
//        return userAttendPlanService.queryPkUserAttendPlanByPlanId(pid);
//    }
}

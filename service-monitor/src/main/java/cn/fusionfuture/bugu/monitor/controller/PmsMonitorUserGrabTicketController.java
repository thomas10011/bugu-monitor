package cn.fusionfuture.bugu.monitor.controller;


import cn.fusionfuture.bugu.monitor.service.IPmsMonitorUserGrabTicketService;
import cn.fusionfuture.bugu.monitor.vo.plan.BasicMonitorPlanVO;
import com.github.pagehelper.PageInfo;
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
 * @since 2020-09-11
 */
@RestController
@Api(tags = "抢票" )
public class PmsMonitorUserGrabTicketController {

    @Autowired
    IPmsMonitorUserGrabTicketService monitorUserGrabTicketService;

    @ApiOperation(value = "用户进行抢票操作")
    @PostMapping("/monitor-plan/grab-ticket")
    public long userGrabTicket(@ApiParam(value = "用户id") @RequestParam Long userId,
                               @ApiParam(value = "计划id") @RequestParam Long planId){
        return monitorUserGrabTicketService.userGrabTicket(userId, planId);
    }

    @GetMapping(value="/grab-ticket/{uid}")
    @ApiOperation(value = "根据用户id查询用户参与投票的计划")
    public PageInfo<BasicMonitorPlanVO> queryUserVotePlanByUserId(@ApiParam(value = "用户的id") @PathVariable Long uid,
                                                                  @ApiParam(value = "查询的页码") @RequestParam(name = "pn") Integer pn,
                                                                  @ApiParam(value = "查询的页面大小") @RequestParam(name = "ps") Integer ps){
        return monitorUserGrabTicketService.queryUserVotePlanByUserId(pn,ps,uid);
    }

}

package cn.fusionfuture.bugu.monitor.controller;


import cn.fusionfuture.bugu.monitor.service.IPmsMonitorUserGrabTicketService;
import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}

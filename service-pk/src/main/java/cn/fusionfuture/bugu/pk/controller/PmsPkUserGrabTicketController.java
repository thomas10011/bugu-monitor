package cn.fusionfuture.bugu.pk.controller;


import cn.fusionfuture.bugu.pk.service.IPmsPkUserGrabTicketService;
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
 * @since 2020-08-24
 */
@RestController
@Api(tags = "抢票" )
public class PmsPkUserGrabTicketController {

    @Autowired
    IPmsPkUserGrabTicketService pkUserGrabTicketService;

    @PostMapping("/grab-ticket")
    @ApiOperation(value = "用户进行抢票操作")
    public Long userGrabTicket(@ApiParam(value = "用户id") @RequestParam Long userId,
                               @ApiParam(value = "计划id") @RequestParam Long planId){
        return pkUserGrabTicketService.userGrabTicket(userId,planId);
    }

}

package cn.fusionfuture.bugu.pk.controller;


import cn.fusionfuture.bugu.pk.service.IPmsPkUserGrabTicketService;
import cn.fusionfuture.bugu.pk.vo.plan.BasicPkPlanVO;
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
 * @since 2020-08-24
 */
@RestController
@Api(tags = "抢票" )
public class PmsPkUserGrabTicketController {

    @Autowired
    IPmsPkUserGrabTicketService pkUserGrabTicketService;

    @PostMapping(value = "/grab-ticket")
    @ApiOperation(value = "用户进行抢票操作")
    public Long userGrabTicket(@ApiParam(value = "用户id") @RequestParam Long userId,
                               @ApiParam(value = "计划id") @RequestParam Long planId){
        return pkUserGrabTicketService.userGrabTicket(userId,planId);
    }

    @GetMapping(value="/grab-ticket/{uid}")
    @ApiOperation(value = "根据用户id查询用户参与投票的计划")
    public PageInfo<BasicPkPlanVO> queryUserVotePlanByUserId(@ApiParam(value = "用户的id") @PathVariable Long uid,
                                   @ApiParam(value = "查询的页码") @RequestParam(name = "pn") Integer pn,
                                   @ApiParam(value = "查询的页面大小") @RequestParam(name = "ps") Integer ps){
        return pkUserGrabTicketService.queryUserVotePlanByUserId(pn,ps,uid);
    }

}

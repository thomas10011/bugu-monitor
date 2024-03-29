package cn.fusionfuture.bugu.message.feign;

import cn.fusionfuture.bugu.message.vo.feignvo.BasicPunchVO;
import cn.fusionfuture.bugu.message.vo.feignvo.PunchForMessageDTO;
import cn.fusionfuture.bugu.message.vo.feignvo.SimpleMonitorPlanVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author DELL
 * @version 1.0
 * @class MonitorFeignService
 * @description TODO
 * @date 2020/9/29 12:04
 */
@FeignClient(value = "monitor-service")
public interface MonitorFeignService {

//    计划模式、计划名称、打卡内容、打卡被点赞次数、打卡被认可次数、打卡被否认次数、打卡图片
//    @ApiOperation(value = "根据打卡id查询打卡相关信息")
//    @GetMapping(value = "/punch/basic/{punchId}")
//    CommonResult<BasicPunchVO> queryBasicPunchVO(@Validated @ApiParam(value = "打卡id") @PathVariable(value = "punchId") Long punchId);

    @ApiOperation(value = "根据打卡id查询打卡信息（message_service调用)")
    @GetMapping(value = "/punch/for-message/{punchId}")
    CommonResult<PunchForMessageDTO> queryPunchForMessageDTO(@Validated
                                                      @ApiParam(value = "打卡id") @PathVariable(value = "punchId") Long punchId);

        //计划标题、监督模式、监督人数 TODO: 抢票代表报名？
    @GetMapping(value = "/monitor-plan/simple-info/{planId}")
    @ApiOperation(value= "查询计划简略信息")
    CommonResult<SimpleMonitorPlanVO> querySimpleMonitorPlanVO(@Validated @ApiParam(value = "计划id") @PathVariable(value = "planId") Long planId);

//    点赞数+1
    @PostMapping("/punch/like")
    @ApiOperation(value = "对一条打卡记录进行点赞")
    void like(@ApiParam(value = "打卡id") @RequestParam(value = "punchId") Long punchId);

//    取消点赞
    @PostMapping("/punch/cancelLike")
    @ApiOperation(value = "对一条打卡记录取消点赞")
    void cancelLike(@ApiParam(value = "打卡id") @RequestParam(name = "punchId") Long punchId);

//    投票数+1
    @PostMapping("/monitor/pms-monitor-vote-record/vote")
    @ApiOperation(value = "投票")
    CommonResult<Integer> vote(@ApiParam(value = "用户id") @RequestParam(value = "userId") Long userId,
                     @ApiParam(value = "打卡id") @RequestParam(value = "punchId") Long punchId,
                     @ApiParam(value = "投票结果") @RequestParam(value = "voteResult") Boolean voteResult);

//    TODO:报名人数+1 没有抢票了？
//    @ApiOperation(value = "用户进行抢票操作")
//    @PostMapping("/monitor-plan/grab-ticket")
//    public long userGrabTicket(@ApiParam(value = "用户id") @RequestParam(value = "userId") Long userId,
//                               @ApiParam(value = "计划id") @RequestParam(value = "planId") Long planId);
}

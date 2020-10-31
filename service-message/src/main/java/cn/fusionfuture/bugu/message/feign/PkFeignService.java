package cn.fusionfuture.bugu.message.feign;

import cn.fusionfuture.bugu.message.vo.feignvo.PunchForMessageDTO;
import cn.fusionfuture.bugu.message.vo.feignvo.PunchWithImageVO;
import cn.fusionfuture.bugu.message.vo.feignvo.SimplePkPlanVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author thomas
 * @description feign调用pk-service
 * @create 2020/8/20 3:26 下午
 * @update 2020/8/20 3:26 下午
 **/
@FeignClient(value = "pk-service")
public interface PkFeignService {
    /**
     * @author thomas
     * @description 远程调用pk-service的getTest方法
     * @create 2020/8/20 3:31 下午
     * @update 2020/8/20 3:31 下午
     * @return java.lang.String
     **/
//    @GetMapping(value = "/feign")
//    String getTest();
//    @ApiOperation(value = "根据打卡id查询打卡相关信息(打卡日历下的打卡信息）")
//    @GetMapping(value = "/punch/basic/{punchId}")
//     CommonResult<PunchWithImageVO> queryPunchWithImageVO(@Validated
//                                                  @ApiParam(value = "打卡id") @PathVariable(value = "punchId") Long punchId);
    @ApiOperation(value = "根据打卡id查询打卡信息（message_service调用)")
    @GetMapping(value = "/punch/for-message/{punchId}")
    CommonResult<PunchForMessageDTO> queryPunchForMessageDTO(@Validated
                                                      @ApiParam(value = "打卡id") @PathVariable(value = "punchId") Long punchId);

        @GetMapping(value = "/pk-plan/simple-info/{planId}")
    @ApiOperation(value= "查询计划简略信息")
    CommonResult<SimplePkPlanVO> querySimplePkPlanVO(@Validated @ApiParam(value = "计划id") @PathVariable(value = "planId") Long planId);

    @PostMapping("/punch/like")
    @ApiOperation(value = "对一条打卡记录进行点赞")
    void like(@ApiParam(value = "打卡id") @RequestParam(value = "punchId") Long punchId);

    @PostMapping("/punch/cancelLike")
    @ApiOperation(value = "对一条打卡记录取消点赞")
    public void cancleLike(@ApiParam(value = "打卡id") @RequestParam(name = "punchId") Long punchId);

    @PostMapping("/vote")
    @ApiOperation(value = "投票")
    CommonResult<Integer> vote(@ApiParam(value = "用户id") @RequestParam(value = "userId") Long userId,
                     @ApiParam(value = "打卡id") @RequestParam(value = "punchId") Long punchId,
                     @ApiParam(value = "投票结果") @RequestParam(value = "voteResult") Boolean voteResult);

//    报名
    @PostMapping(value = "/attend")
    @ApiOperation(value = "用户参与pk计划")
    CommonResult<Long> punch(@ApiParam(value = "用户id") @RequestParam(value = "userId") Long userId,
                      @ApiParam(value = "计划id") @RequestParam(value = "planId") Long planId);


}

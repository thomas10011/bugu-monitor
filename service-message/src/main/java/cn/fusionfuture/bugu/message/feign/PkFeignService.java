package cn.fusionfuture.bugu.message.feign;

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
    @ApiOperation(value = "根据打卡id查询打卡相关信息")
    @GetMapping(value = "/punch/detail/{punchId}")
    CommonResult<PunchWithImageVO> queryPunchWithImageVO(@Validated @ApiParam(value = "打卡id") @PathVariable(value = "punchId") Long punchId);

    @GetMapping(value = "/pk-plan/simple-info/{planId}")
    @ApiOperation(value= "查询计划简略信息")
    CommonResult<SimplePkPlanVO> querySimplePkPlanVO(@Validated @ApiParam(value = "计划id") @PathVariable(value = "planId") Long planId);

    @PostMapping("/punch/like")
    @ApiOperation(value = "对一条打卡记录进行点赞")
    void like(@ApiParam(value = "打卡id") @RequestParam(name = "punchId") Long punchId);

//    @PostMapping("/vote")
//    @ApiOperation(value = "投票")
//    public void vote(@ApiParam(value = "用户id") @RequestParam Long userId,
//                     @ApiParam(value = "打卡id") @RequestParam Long punchId,
//                     @ApiParam(value = "投票结果") @RequestParam Boolean voteResult);
//
//    @PostMapping(value = "/attend")
//    @ApiOperation(value = "用户参与pk计划")
//    public Long punch(@ApiParam(value = "用户id") @RequestParam Long userId,
//                      @ApiParam(value = "计划id") @RequestParam Long planId);

}

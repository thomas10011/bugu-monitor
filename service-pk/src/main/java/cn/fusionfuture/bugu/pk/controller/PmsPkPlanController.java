package cn.fusionfuture.bugu.pk.controller;


import cn.fusionfuture.bugu.pk.dto.PlanForMessageDTO;
import cn.fusionfuture.bugu.pk.dto.PopularPlanDTO;
import cn.fusionfuture.bugu.pk.feign.SearchFeignService;
import cn.fusionfuture.bugu.pk.service.IPmsPkPlanService;
import cn.fusionfuture.bugu.pk.vo.plan.*;
import cn.fusionfuture.bugu.pojo.constants.MonitorPlanType;
import cn.fusionfuture.bugu.pojo.constants.PkPlanStatus;
import cn.fusionfuture.bugu.pojo.constants.PkPlanType;
import cn.fusionfuture.bugu.utils.oss.OssUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
@RestController
@Api(tags="创建和查询pk计划")
public class PmsPkPlanController {

    @Autowired
    IPmsPkPlanService pkPlanService;

    @ApiOperation(value = "创建一个pk计划")
    @PostMapping(value = "/plan")
    public Long createPkPlan(@RequestBody NewPkPlanVO newPkPlanVO) {

        Long id=pkPlanService.createPkPlan(newPkPlanVO);
        return id;
    }

    @GetMapping(value = "/plan/{userId}")
    @ApiOperation(value = "根据用户id查询pk计划")
    public PageInfo<BasicPkPlanVO> queryBasicPkPlanVO(@Validated
                                                      @ApiParam(value = "用户id") @PathVariable(value = "userId") Long userId,
                                                      @ApiParam(value = "页面编号") @RequestParam(name = "pn", defaultValue = "1") Integer pn,
                                                      @ApiParam(value = "页面大小") @RequestParam(name = "ps", defaultValue = "5") Integer ps) {
        return pkPlanService.queryBasicPkPlanVO(pn, ps, userId);
    }

    @GetMapping(value = "/plan/simple-info/{planId}")
    @ApiOperation(value= "查询计划简略信息")
    public SimplePkPlanVO querySimplePkPlanVO(@Validated
                                                  @ApiParam(value = "计划id") @PathVariable(value = "planId") Long planId){
        return pkPlanService.querySimplePkPlanVO(planId);
    }

    @GetMapping(value = "/plan/plan-vo/{planId}")
    @ApiOperation(value= "查询计划信息")
    public PkPlanVO queryPkPlanVO(@Validated
                                              @ApiParam(value = "计划id") @PathVariable(value = "planId") Long planId){
        return pkPlanService.queryPkPlanVO(planId);
    }

    @GetMapping(value = "/plan/detailed-info/{uid}&{pid}")
    @ApiOperation(value= "查询计划详细信息")
    public DetailedPkPlanVO queryDetailedPkPlanVO(@Validated
                                                  @ApiParam(value = "用户id") @RequestParam(name = "uid") Long uid,
                                                  @ApiParam(value = "计划id") @RequestParam(name = "pid") Long pid){
        return pkPlanService.queryDetailedPkPlanVO(uid,pid);
    }

    @PostMapping(value = "/plan/check-isPunched")
    @ApiOperation(value= "查询当前时间计划打卡状态")
    public String checkIsPunched(@ApiParam(value = "用户id") @RequestParam(name = "userId") Long userId,
                                 @ApiParam(value = "计划id") @RequestParam(name = "planId") Long planId){
        return pkPlanService.checkIsPunched(userId,planId);
    }

    @GetMapping(value = "/plan/for-message/{planId}")
    @ApiOperation(value= "根据计划id查询计划信息（message_service调用)")
    public PlanForMessageDTO queryPlanForMessageDTO(@Validated
                                                    @ApiParam(value = "计划id") @PathVariable(value = "planId") Long planId){
        return pkPlanService.getPlanForMessageDTO(planId);
    }

    @GetMapping("/plan/policy")
    @ApiOperation(value = "获取上传图片需要的policy")
    public Map<String, String> getPolicy() throws IOException, ServletException {
        return OssUtil.getPolicy("plan/");
    }

    @GetMapping("/plan/starttime")
    @ApiOperation(value = "查询一个计划的开始时间")
    public LocalDateTime getStartTime(@ApiParam(value = "计划id") @PathVariable(value = "pid") Long pid) {
        return pkPlanService.getById(pid).getStartTime();
    }

    @GetMapping("/plan/endtime")
    @ApiOperation(value = "查询一个计划的开始时间")
    public LocalDateTime getEndTime(@ApiParam(value = "计划id") @PathVariable(value = "pid") Long pid) {
        return pkPlanService.getById(pid).getEndTime();
    }

}

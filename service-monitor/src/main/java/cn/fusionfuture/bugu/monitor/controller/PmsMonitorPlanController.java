package cn.fusionfuture.bugu.monitor.controller;


import cn.fusionfuture.bugu.monitor.dto.PlanForMessageDTO;
import cn.fusionfuture.bugu.monitor.dto.PopularPlanDTO;
import cn.fusionfuture.bugu.monitor.feign.SearchFeignService;
import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPlanService;
import cn.fusionfuture.bugu.monitor.vo.plan.BasicMonitorPlanVO;
import cn.fusionfuture.bugu.monitor.vo.plan.DetailedMonitorPlanVO;
import cn.fusionfuture.bugu.monitor.vo.plan.NewMonitorPlanVO;
import cn.fusionfuture.bugu.monitor.vo.plan.SimpleMonitorPlanVO;
import cn.fusionfuture.bugu.pojo.constants.MonitorPlanStatus;
import cn.fusionfuture.bugu.pojo.constants.MonitorPlanType;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
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
@Api(tags = "创建和查询监督计划")
public class PmsMonitorPlanController {

    @Autowired
    IPmsMonitorPlanService monitorPlanService;

    @Autowired
    SearchFeignService searchFeignService;

    @ApiOperation(value = "创建监督计划")
    @PostMapping(value = "/monitor-plan")
    public Long createMonitorPlan(@RequestBody NewMonitorPlanVO newMonitorPlanVO) {
        Long id = monitorPlanService.createMonitorPlan(newMonitorPlanVO);
        PopularPlanDTO popularPlanDTO = new PopularPlanDTO();
        popularPlanDTO
                .setId(id)
                .setUid(newMonitorPlanVO.getUserId())
                .setTt(newMonitorPlanVO.getName())
                .setTp(MonitorPlanType.getValue(newMonitorPlanVO.getMonitorPatternId()))
                .setCv(newMonitorPlanVO.getImageUrl())
                .setHc(0)
                .setRt(0)
                .setSt(MonitorPlanStatus.REGISTERING.getValue())
                .setAw(newMonitorPlanVO.getTotalBonus());
        searchFeignService.createPopularPlan(popularPlanDTO);
        return id;
    }

    @ApiOperation(value = "根据用户id分页查询监督计划")
    @GetMapping(value = "/monitor-plan/{uid}")
    public PageInfo<BasicMonitorPlanVO> queryBasicMonitorPlanVO(@Validated
                                                                @ApiParam(value = "用户id") @NonNull @PathVariable(value = "uid") Long uid,
                                                                @ApiParam(value = "页码编号") @RequestParam(name = "pn", defaultValue = "1") Integer pn,
                                                                @ApiParam(value = "页面大小") @RequestParam(name = "ps", defaultValue = "5") Integer ps) {
        return monitorPlanService.queryBasicMonitorPlanVO(pn, ps, uid);
    }

    @GetMapping(value = "/monitor-plan/detailed-info/{pid}")
    @ApiOperation(value= "查询计划详细信息")
    public DetailedMonitorPlanVO queryDetailedMonitorPlanVO(@Validated
                                                        @ApiParam(value = "计划id") @PathVariable(value = "pid") Long pid){
        return monitorPlanService.queryDetailedMonitorPlanVO(pid);
    }

    @GetMapping(value = "/monitor-plan/simple-info/{planId}")
    @ApiOperation(value= "查询计划简略信息")
    public SimpleMonitorPlanVO querySimpleMonitorPlanVO(@Validated
                                              @ApiParam(value = "计划id") @PathVariable(value = "planId") Long planId){
        return monitorPlanService.querySimpleMonitorPlanVO(planId);
    }

    @GetMapping(value = "/monitor-plan/check-isPunched/{planId}")
    @ApiOperation(value= "查询当前时间计划打卡状态")
    public String checkIsPunched(@Validated
                                     @ApiParam(value = "计划id") @PathVariable(value = "planId") Long planId){
        return monitorPlanService.checkIsPunched(planId);
    }

    @GetMapping(value = "/monitor-plan/for-message/{planId}")
    @ApiOperation(value= "根据计划id查询计划信息（message_service调用)")
    public PlanForMessageDTO queryPlanForMessageDTO(@Validated
                                 @ApiParam(value = "计划id") @PathVariable(value = "planId") Long planId){
        return monitorPlanService.getPlanForMessageDTO(planId);
    }



}

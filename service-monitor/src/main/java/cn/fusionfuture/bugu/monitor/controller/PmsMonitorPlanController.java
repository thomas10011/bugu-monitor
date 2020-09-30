package cn.fusionfuture.bugu.monitor.controller;


import cn.fusionfuture.bugu.monitor.dto.PopularPlanDTO;
import cn.fusionfuture.bugu.monitor.feign.SearchFeignService;
import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPlanService;
import cn.fusionfuture.bugu.monitor.vo.BasicMonitorPlanVO;
import cn.fusionfuture.bugu.monitor.vo.NewMonitorPlanVO;
import cn.fusionfuture.bugu.monitor.vo.SimpleMonitorPlanVO;
import cn.fusionfuture.bugu.pojo.constants.MonitorPlanStatus;
import cn.fusionfuture.bugu.pojo.constants.MonitorPlanType;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPlan;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
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
@Api(tags = "创建和查询监督计划")
public class PmsMonitorPlanController {

    @Autowired
    IPmsMonitorPlanService monitorPlanService;

    @Autowired
    SearchFeignService searchFeignService;

    @ApiOperation(value = "创建新的监督计划")
    @PostMapping(value = "/monitor-plan")
    public Long createMonitorPlan(@RequestBody NewMonitorPlanVO newMonitorPlanVO) {
        Long id = monitorPlanService.createMonitorPlan(newMonitorPlanVO);
        PopularPlanDTO popularPlanDTO = new PopularPlanDTO();
        popularPlanDTO
                .setId(id)
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

    @GetMapping(value = "/monitor-plan/simple-info/{planId}")
    @ApiOperation(value= "查询计划简略信息")
    public SimpleMonitorPlanVO querySimpleMonitorPlanVO(@Validated
                                              @ApiParam(value = "计划id") @PathVariable(value = "planId") Long planId){
        return monitorPlanService.querySimpleMonitorPlanVO(planId);
    }
}

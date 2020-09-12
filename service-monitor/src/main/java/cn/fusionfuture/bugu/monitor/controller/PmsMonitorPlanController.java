package cn.fusionfuture.bugu.monitor.controller;


import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPlanService;
import cn.fusionfuture.bugu.monitor.vo.BasicMonitorPlanVO;
import cn.fusionfuture.bugu.monitor.vo.NewMonitorPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPlan;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PmsMonitorPlanController {

    @Autowired
    IPmsMonitorPlanService monitorPlanService;

    @PostMapping(value = "/monitor-plan")
    public Long createMonitorPlan(@RequestBody NewMonitorPlanVO newMonitorPlanVO) {
        return monitorPlanService.createMonitorPlan(newMonitorPlanVO);
    }


    @GetMapping(value = "/monitor-plan/{uid}")
    public PageInfo<BasicMonitorPlanVO> queryBasicMonitorPlanVO(@Validated
                                                                @PathVariable(value = "uid") Long uid,
                                                                @RequestParam Integer pn,
                                                                @RequestParam Integer ps) {
        return monitorPlanService.queryBasicMonitorPlanVO(pn, ps, uid);
    }
}

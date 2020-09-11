package cn.fusionfuture.bugu.monitor.controller;


import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPlanService;
import cn.fusionfuture.bugu.monitor.vo.BasicMonitorPlanVO;
import cn.fusionfuture.bugu.monitor.vo.NewMonitorPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPlan;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "testLongType")
    public Long testLongType() {
        return 1304393772401971208L;
    }

    @GetMapping(value = "/monitor-plan/{uid}")
    public List<BasicMonitorPlanVO> queryBasicMonitorPlanVO(@PathVariable(value = "uid") Long uid) {
        return monitorPlanService.queryBasicMonitorPlanVO(uid);
    }
}

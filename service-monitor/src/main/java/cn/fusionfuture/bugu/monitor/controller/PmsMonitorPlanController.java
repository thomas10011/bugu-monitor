package cn.fusionfuture.bugu.monitor.controller;


import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPlanService;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class PmsMonitorPlanController {

    @Autowired
    IPmsMonitorPlanService monitorPlanService;

    @PostMapping(value = "/monitor-plan")
    public Long createMonitorPlan(@RequestBody PmsMonitorPlan monitorPlan) {
        return monitorPlanService.createMonitorPlan(monitorPlan);
    }
}

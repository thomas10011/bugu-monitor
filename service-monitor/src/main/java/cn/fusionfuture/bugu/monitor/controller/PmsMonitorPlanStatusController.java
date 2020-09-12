package cn.fusionfuture.bugu.monitor.controller;


import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPlanStatusService;
import cn.fusionfuture.bugu.monitor.vo.MonitorPlanStatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author thomas
 * @since 2020-09-11
 */
@RestController
@RequestMapping("/monitor/pms-monitor-plan-status")
public class PmsMonitorPlanStatusController {

    @Autowired
    IPmsMonitorPlanStatusService monitorPlanStatusService;

    @GetMapping(value = "/monitor-plan/staus")
    public List<MonitorPlanStatusVO> queryMonitorPlanStatus() {
        return monitorPlanStatusService.queryMonitorPlanStatus();
    }

}

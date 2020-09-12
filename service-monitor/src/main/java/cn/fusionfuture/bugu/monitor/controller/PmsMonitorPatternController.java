package cn.fusionfuture.bugu.monitor.controller;


import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPatternService;
import cn.fusionfuture.bugu.monitor.vo.MonitorPlanPatternVO;
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
 * @since 2020-08-24
 */
@RestController
public class PmsMonitorPatternController {

    @Autowired
    IPmsMonitorPatternService monitorPatternService;

    @GetMapping(value = "/monitor-plan/pattern")
    public List<MonitorPlanPatternVO> queryMonitorPlanPatter() {
        return monitorPatternService.queryMonitorPlanPatter();
    }

}

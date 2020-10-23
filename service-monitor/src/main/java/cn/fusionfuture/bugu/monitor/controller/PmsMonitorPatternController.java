package cn.fusionfuture.bugu.monitor.controller;


import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPatternService;
import cn.fusionfuture.bugu.monitor.vo.MonitorPlanPatternVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "查询计划类型")
public class PmsMonitorPatternController {

    @Autowired
    IPmsMonitorPatternService monitorPatternService;

    @GetMapping(value = "/monitor-plan/pattern")
    @ApiOperation(value = "查询计划类型（example：单人监督）")
    public List<MonitorPlanPatternVO> queryMonitorPlanPatter() {
        return monitorPatternService.queryMonitorPlanPatter();
    }

}

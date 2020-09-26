package cn.fusionfuture.bugu.monitor.controller;


import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPlanStatusService;
import cn.fusionfuture.bugu.monitor.vo.MonitorPlanStatusVO;
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
 * @since 2020-09-11
 */
@RestController
@Api(tags = "查询打卡的状态列表")
public class PmsMonitorPlanStatusController {

    @Autowired
    IPmsMonitorPlanStatusService monitorPlanStatusService;

    @GetMapping(value = "/monitor-plan/status")
    @ApiOperation(value = "查询当前打卡所有的状态")
    public List<MonitorPlanStatusVO> queryMonitorPlanStatus() {
        return monitorPlanStatusService.queryMonitorPlanStatus();
    }

}

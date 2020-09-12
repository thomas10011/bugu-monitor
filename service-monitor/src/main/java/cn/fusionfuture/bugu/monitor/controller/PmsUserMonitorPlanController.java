package cn.fusionfuture.bugu.monitor.controller;


import cn.fusionfuture.bugu.monitor.service.IPmsUserMonitorPlanService;
import cn.fusionfuture.bugu.monitor.vo.BasicMonitorPlanVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PmsUserMonitorPlanController {

    @Autowired
    IPmsUserMonitorPlanService userMonitorPlanService;


    @GetMapping(value = "/monitor-plan/monitor/{uid}")
    PageInfo<BasicMonitorPlanVO> queryMonitorPlanByUserId(@PathVariable Long uid, @RequestParam Integer pn, @RequestParam Integer ps) {
        return userMonitorPlanService.queryMonitorPlanByUserId(pn, ps, uid);
    }

}

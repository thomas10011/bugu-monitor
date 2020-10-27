package cn.fusionfuture.bugu.monitor.controller;


import cn.fusionfuture.bugu.monitor.service.IPmsUserMonitorPlanService;
import cn.fusionfuture.bugu.monitor.vo.BasicMonitorPlanVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "根据用户id查询用户参与的监督计划")
public class PmsUserMonitorPlanController {

    @Autowired
    IPmsUserMonitorPlanService userMonitorPlanService;


    @GetMapping(value = "/monitor-plan/monitor/{uid}")
    @ApiOperation(value = "根据用户id查询用户创建监督计划")
    PageInfo<BasicMonitorPlanVO> queryMonitorPlanByUserId(@ApiParam(value = "用户的id") @PathVariable Long uid,
                                                          @ApiParam(value = "查询的页码") @RequestParam Integer pn,
                                                          @ApiParam(value = "查询的页面大小") @RequestParam Integer ps) {
        return userMonitorPlanService.queryMonitorPlanByUserId(pn, ps, uid);
    }

}

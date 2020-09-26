package cn.fusionfuture.bugu.pk.controller;


import cn.fusionfuture.bugu.pk.service.IPmsPkPlanStatusService;
import cn.fusionfuture.bugu.pk.vo.PkPlanStatusVO;
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
@RequestMapping("/pk/pms-pk-plan-status")
@Api(tags = "查询pk计划的状态")
public class PmsPkPlanStatusController {

    @Autowired
    IPmsPkPlanStatusService pkPlanStatusService;

    @ApiOperation(value = "查询显示pk计划的所有状态")
    @GetMapping(value = "/pk-plan/status")
    public List<PkPlanStatusVO> queryPkPlanStatus() {
        return pkPlanStatusService.queryPkPlanStatus();
    }
}

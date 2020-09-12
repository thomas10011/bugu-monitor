package cn.fusionfuture.bugu.pk.controller;


import cn.fusionfuture.bugu.pk.service.IPmsPkPlanStatusService;
import cn.fusionfuture.bugu.pk.vo.PkPlanPatternVO;
import cn.fusionfuture.bugu.pk.vo.PkPlanStatusVO;
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

public class PmsPkPlanStatusController {

    @Autowired
    IPmsPkPlanStatusService pkPlanStatusService;

    @GetMapping(value = "/pk-plan/status")
    public List<PkPlanStatusVO> queryPkPlanStatus() {
        return pkPlanStatusService.queryPkPlanStatus();
    }
}

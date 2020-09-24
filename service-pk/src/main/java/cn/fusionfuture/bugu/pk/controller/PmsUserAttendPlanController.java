package cn.fusionfuture.bugu.pk.controller;


import cn.fusionfuture.bugu.pk.service.IPmsUserAttendPlanService;
import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
import com.github.pagehelper.PageInfo;
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
public class PmsUserAttendPlanController {

    @Autowired
    IPmsUserAttendPlanService userAttendPlanService;

    @GetMapping(value = "/pk-plan/attend-plan/{uid}")
    PageInfo <BasicPkPlanVO> queryPkUserAttendPlanByUserId(@PathVariable Long uid, @RequestParam Integer pn, @RequestParam Integer ps) {
        return userAttendPlanService.queryPkUserAttendPlanByUserId(pn,ps,uid);
    }

    @PostMapping("/attend")
    public Long punch(@RequestParam Long userId,
                      @RequestParam Long planId) {
        return userAttendPlanService.userAttendPlan(userId,planId);
    }

//    @GetMapping(value = "/pk-plan/pk/{pid}")
//    BasicPkPlanVO queryPkUserAttendPlanByPlanId(@PathVariable Long pid) {
//        return userAttendPlanService.queryPkUserAttendPlanByPlanId(pid);
//    }
}

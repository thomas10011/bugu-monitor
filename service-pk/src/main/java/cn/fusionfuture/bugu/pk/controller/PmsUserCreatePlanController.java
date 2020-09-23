package cn.fusionfuture.bugu.pk.controller;

import cn.fusionfuture.bugu.pk.service.IPmsUserAttendPlanService;
import cn.fusionfuture.bugu.pk.service.IPmsUserCreatePlanService;
import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zws
 * @since 2020-09-15
 */
@RestController
public class PmsUserCreatePlanController {

    @Autowired
    IPmsUserCreatePlanService userCreatePlanService;

    @GetMapping(value = "/pk-plan/create-plan/{uid}")
    PageInfo<BasicPkPlanVO> queryPkUserCreatePlanByUserId(@PathVariable Long uid, @RequestParam Integer pn, @RequestParam Integer ps) {
        return userCreatePlanService.queryPkUserCreatePlanByUserId(pn,ps,uid);
    }
}
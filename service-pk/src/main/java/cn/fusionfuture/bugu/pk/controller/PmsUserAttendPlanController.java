package cn.fusionfuture.bugu.pk.controller;


import cn.fusionfuture.bugu.pk.service.IPmsUserAttendPlanService;
import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
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
public class PmsUserAttendPlanController {

    @Autowired
    IPmsUserAttendPlanService userPkPlanService;

    @GetMapping(value = "/pk-plan/pk/{uid}")
    PageInfo <BasicPkPlanVO> queryPkPlanByUserId(@PathVariable Long uid, @RequestParam Integer pn, @RequestParam Integer ps) {
        return userPkPlanService.queryPkPlanByUserId(pn,ps,uid);
    }
}

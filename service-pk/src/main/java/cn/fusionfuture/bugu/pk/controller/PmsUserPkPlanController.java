package cn.fusionfuture.bugu.pk.controller;


import cn.fusionfuture.bugu.pk.service.IPmsPkPatternService;
import cn.fusionfuture.bugu.pk.service.IPmsUserPkPlanService;
import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.PkPlanPatternVO;
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
@RequestMapping("/pk/pms-user-pk-plan")
public class PmsUserPkPlanController {

    @Autowired
    IPmsUserPkPlanService userPkPlanService;

    @GetMapping(value = "/pk-plan/pk/{uid}")
    PageInfo <BasicPkPlanVO> queryPkPlanByUserId(@PathVariable Long uid, @RequestParam Integer pn, @RequestParam Integer ps) {
        return userPkPlanService.queryPkPlanByUserId(pn,ps,uid);
    }
}

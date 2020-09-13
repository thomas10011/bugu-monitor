package cn.fusionfuture.bugu.pk.controller;


import cn.fusionfuture.bugu.pk.service.IPmsPkPlanService;
import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.NewPkPlanVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
public class PmsPkPlanController {

    @Autowired
    IPmsPkPlanService pkPlanService;

    @PostMapping(value = "/pk-plan")
    public Long createPkPlan(@RequestBody NewPkPlanVO newPkPlanVO) {
        return pkPlanService.createPkPlan(newPkPlanVO);
    }


    @GetMapping(value = "/pk-plan/{uid}")
    public PageInfo<BasicPkPlanVO> queryBasicPkPlanVO(@Validated
                                                          @PathVariable(value = "uid") Long uid,
                                                      @RequestParam Integer pn,
                                                      @RequestParam Integer ps) {
        return pkPlanService.queryBasicPkPlanVO(pn, ps, uid);
    }

}

package cn.fusionfuture.bugu.pk.controller;


import cn.fusionfuture.bugu.pk.service.IPmsPkPlanService;
import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.NewPkPlanVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags="创建和查询pk计划")
public class PmsPkPlanController {

    @Autowired
    IPmsPkPlanService pkPlanService;

    @ApiOperation(value = "创建一个pk计划")
    @PostMapping(value = "/pk-plan")
    public Long createPkPlan(@RequestBody NewPkPlanVO newPkPlanVO) {
        return pkPlanService.createPkPlan(newPkPlanVO);
    }

    @ApiOperation(value = "根据用户id查询pk计划")
    @GetMapping(value = "/pk-plan/{uid}")
    public PageInfo<BasicPkPlanVO> queryBasicPkPlanVO(@Validated
                                                      @ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                                                      @ApiParam(value = "页面编号") @RequestParam(name = "pn", defaultValue = "1") Integer pn,
                                                      @ApiParam(value = "页面大小") @RequestParam(name = "ps", defaultValue = "5") Integer ps) {
        return pkPlanService.queryBasicPkPlanVO(pn, ps, uid);
    }

}

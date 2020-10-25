package cn.fusionfuture.bugu.pk.controller;


import cn.fusionfuture.bugu.pk.dto.PopularPlanDTO;
import cn.fusionfuture.bugu.pk.feign.SearchFeignService;
import cn.fusionfuture.bugu.pk.service.IPmsPkPlanService;
import cn.fusionfuture.bugu.pk.vo.BasicPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.DetailedPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.NewPkPlanVO;
import cn.fusionfuture.bugu.pk.vo.SimplePkPlanVO;
import cn.fusionfuture.bugu.pojo.constants.MonitorPlanStatus;
import cn.fusionfuture.bugu.pojo.constants.MonitorPlanType;
import cn.fusionfuture.bugu.pojo.constants.PkPlanStatus;
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

    @Autowired
    SearchFeignService searchFeignService;

    @ApiOperation(value = "创建一个pk计划")
    @PostMapping(value = "/pk-plan")
    public Long createPkPlan(@RequestBody NewPkPlanVO newPkPlanVO) {

        Long id=pkPlanService.createPkPlan(newPkPlanVO);
        PopularPlanDTO popularPlanDTO = new PopularPlanDTO();
        popularPlanDTO
                .setId(id)
                .setUid(newPkPlanVO.getUserId())
                .setTt(newPkPlanVO.getName())
                .setTp(MonitorPlanType.getValue(newPkPlanVO.getPkPatternId()))
                .setCv(newPkPlanVO.getImageUrl())
                .setHc(0)
                .setRt(0)
                .setSt(PkPlanStatus.REGISTERING.getValue())
                .setAw(newPkPlanVO.getTotalBonus());
        searchFeignService.createPopularPlan(popularPlanDTO);
        return id;
    }

    @GetMapping(value = "/pk-plan/{userId}")
    @ApiOperation(value = "根据用户id查询pk计划")
    public PageInfo<BasicPkPlanVO> queryBasicPkPlanVO(@Validated
                                                      @ApiParam(value = "用户id") @PathVariable(value = "userId") Long userId,
                                                      @ApiParam(value = "页面编号") @RequestParam(name = "pn", defaultValue = "1") Integer pn,
                                                      @ApiParam(value = "页面大小") @RequestParam(name = "ps", defaultValue = "5") Integer ps) {
        return pkPlanService.queryBasicPkPlanVO(pn, ps, userId);
    }

    @GetMapping(value = "/pk-plan/simple-info/{planId}")
    @ApiOperation(value= "查询计划简略信息")
    public SimplePkPlanVO querySimplePkPlanVO(@Validated
                                                  @ApiParam(value = "计划id") @PathVariable(value = "planId") Long planId){
        return pkPlanService.querySimplePkPlanVO(planId);
    }

    @GetMapping(value = "/pk-plan/detailed-info/{uid}&{pid}")
    @ApiOperation(value= "查询计划详细信息")
    public DetailedPkPlanVO queryDetailedPkPlanVO(@Validated
                                                  @ApiParam(value = "用户id") @RequestParam(name = "uid") Long uid,
                                                  @ApiParam(value = "计划id") @RequestParam(name = "pid") Long pid){
        return pkPlanService.queryDetailedPkPlanVO(uid,pid);
    }

    @PostMapping(value = "/pk-plan/check-isPunched")
    @ApiOperation(value= "查询当前时间计划打卡状态")
    public String checkIsPunched(@ApiParam(value = "用户id") @RequestParam(name = "userId") Long userId,
                                 @ApiParam(value = "计划id") @RequestParam(name = "planId") Long planId){
        return pkPlanService.checkIsPunched(userId,planId);
    }

}

package cn.fusionfuture.bugu.search.controller;

import cn.fusionfuture.bugu.search.service.IPopularPunchService;
import cn.fusionfuture.bugu.search.service.impl.PopularPunchServiceImpl;
import cn.fusionfuture.bugu.search.vo.PlanTrendVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zws
 * @version 1.0
 * @class PopularPunchController
 * @description TODO
 * @date 2020/10/20 16:35
 */
@RestController
@Api(tags = "查询首页动态信息")
public class PopularPunchController {

    @Autowired
    IPopularPunchService popularPunchService;

    @ApiOperation(value = "根据用户id显示动态界面")
    @GetMapping(value = "/popular/punch/{uid}")
    public PageInfo<PlanTrendVO> queryPlanTrendVO(@Validated
                                                                @ApiParam(value = "用户id") @NonNull @PathVariable(value = "uid") Long uid,
                                                  @ApiParam(value = "页码编号") @RequestParam(name = "pn", defaultValue = "1") Integer pn,
                                                  @ApiParam(value = "页面大小") @RequestParam(name = "ps", defaultValue = "5") Integer ps) {
        return popularPunchService.queryPopularPunch(pn,ps,uid);
    }
}
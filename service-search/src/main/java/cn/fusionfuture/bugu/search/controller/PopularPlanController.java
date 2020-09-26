package cn.fusionfuture.bugu.search.controller;

import cn.fusionfuture.bugu.pojo.constants.MonitorPlanType;
import cn.fusionfuture.bugu.pojo.constants.PkPlanStatus;
import cn.fusionfuture.bugu.pojo.constants.PkPlanType;
import cn.fusionfuture.bugu.search.service.IPopularPlanService;
import cn.fusionfuture.bugu.search.vo.PopularPlanVO;
import cn.hutool.json.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author thomas
 * @version 1.0
 * @class PopularPlanController
 * @description 查询计划首页的接口
 * @date 2020/9/24 7:57 上午
 */
@RestController
@Api(tags = "查询首页热门的接口")
public class PopularPlanController {

    @Autowired
    IPopularPlanService popularPlanService;

    @GetMapping(value = "/popular")
    @ApiOperation(value = "按照查询条件查询首页热门")
    public PopularPlanVO queryPopularPlan(@ApiParam(value = "计划标题的关键字") @RequestParam(value = "kw", defaultValue = "") String keyWord,
                                          @ApiParam(value = "查询的页码") @RequestParam(value = "pn", defaultValue = "1") Integer pageNum,
                                          @ApiParam(value = "页面的大小") @RequestParam(value = "ps", defaultValue = "5") Integer pageSize,
                                          @ApiParam(value = "计划的类型") @RequestParam(value = "mpt", defaultValue = "") List<String> planType,
                                          @ApiParam(value = "计划的状态") @RequestParam(value = "mps", defaultValue = "") List<String> planStatus) throws IOException {
        planType = planType.contains(PkPlanType.ALL) || planType.isEmpty() ? null : planType;
        planStatus = planStatus.contains(PkPlanStatus.ALL) || planStatus.isEmpty() ? null : planStatus;
        return popularPlanService.queryPopularPlan(keyWord, pageNum, pageSize, planType, planStatus);

    }

}

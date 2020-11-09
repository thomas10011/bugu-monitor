package cn.fusionfuture.bugu.search.controller;

import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.constants.MonitorPlanType;
import cn.fusionfuture.bugu.pojo.constants.PkPlanStatus;
import cn.fusionfuture.bugu.pojo.constants.PkPlanType;
import cn.fusionfuture.bugu.search.dto.PopularPlanDTO;
import cn.fusionfuture.bugu.search.service.IPopularPlanService;
import cn.fusionfuture.bugu.search.vo.PopularPlanVO;
import cn.hutool.json.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

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
                                          @ApiParam(value = "计划的类型") @RequestParam(value = "tp") List<String> planType,
                                          @ApiParam(value = "计划的状态") @RequestParam(value = "st") List<String> planStatus) throws IOException {
        planType = planType.contains(PkPlanType.ALL.getValue()) || planType.isEmpty() ? null : planType;
        planStatus = planStatus.contains(PkPlanStatus.ALL.getValue()) || planStatus.isEmpty() ? null : planStatus;
        return popularPlanService.queryPopularPlan(keyWord, pageNum, pageSize, planType, planStatus);

    }

    @GetMapping(value = "/popular/rate")
    @ApiOperation(value = "供计划微服务调用，给一个计划点赞")
    public void ratePopularPlan(@ApiParam(value = "计划的id") @NonNull @RequestParam(value = "pid") Long pid) throws IOException {
        popularPlanService.ratePopularPlan(pid);
    }

    @GetMapping(value = "/popular/cancel-rate")
    @ApiOperation(value = "供计划微服务调用，给一个计划取消点赞")
    public void cancelRatePopularPlan(@ApiParam(value = "计划的id") @NonNull @RequestParam(value = "pid") Long pid) throws IOException {
        popularPlanService.cancelRatePopularPlan(pid);
    }

    @GetMapping(value = "/popular/status")
    @ApiOperation(value = "供计划相关微服务调用，更新计划的状态")
    public void updatePlanStatus(@ApiParam(value = "计划的id") @NonNull @RequestParam(value = "pid") Long pid,
                                 @ApiParam(value = "计划状态的") @NonNull @RequestParam(value = "st") String status) throws IOException {

        popularPlanService.updatePlanStatus(pid, status);

    }

    @GetMapping(value = "/popular/headcount")
    @ApiOperation(value = "供计划相关微服务调用，更新计划的参与人数")
    public void updatePlanHeadcount(@ApiParam(value = "计划的id") @NonNull @RequestParam(value = "pid") Long pid,
                                    @ApiParam(value = "计划参与的人数") @NonNull @RequestParam(value = "hc") Integer hc) throws IOException {
        popularPlanService.updatePlanHeadcount(pid, hc);

    }

    @GetMapping(value = "/popular/avatar")
    @ApiOperation(value = "供计划相关微服务调用，更新用户头像")
    public void updateUserAvatar(@ApiParam(value = "计划的id") @NonNull @RequestParam(value = "pid") Long pid,
                                    @ApiParam(value = "用户头像url") @NonNull @RequestParam(value = "hc") String url) throws IOException {
        popularPlanService.updateUserAvatar(pid, url);

    }

    @PostMapping(value = "popular")
    @ApiOperation(value = "供计划微服务远程调用，创建首页信息")
    public void createPopularPlan(@ApiParam(value = "首页计划的dto") @RequestBody PopularPlanDTO popularPlanDTO) throws IOException {
        popularPlanService.createPopularPlan(popularPlanDTO);
    }


}

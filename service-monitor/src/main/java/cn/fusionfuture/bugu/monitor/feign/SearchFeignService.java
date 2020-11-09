package cn.fusionfuture.bugu.monitor.feign;

import cn.fusionfuture.bugu.monitor.dto.PopularPlanDTO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

/**
 * @author thomas
 * @version 1.0
 * @class SearchFeignService
 * @description TODO
 * @date 2020/9/26 4:30 下午
 */
@FeignClient(name = "search-service")
public interface SearchFeignService {


    /**
     * 创建计划的首页数据
     * @author thomas
     * @since 2020/9/26 4:49 下午
     * @param popularPlanDTO 要保存的信息
     **/
    @PostMapping(value = "popular")
    CommonResult<?> createPopularPlan(@ApiParam(value = "首页计划的dto") @RequestBody PopularPlanDTO popularPlanDTO);

    @GetMapping(value = "/popular/status")
    @ApiOperation(value = "供计划相关微服务调用，更新计划的状态")
    public void updatePlanStatus(@ApiParam(value = "计划的id") @NonNull @RequestParam(value = "pid") Long pid,
                                 @ApiParam(value = "计划状态的") @NonNull @RequestParam(value = "st") String status) throws IOException;

    @GetMapping(value = "/popular/headcount")
    @ApiOperation(value = "供计划相关微服务调用，更新计划的参与人数")
    public void updatePlanHeadcount(@ApiParam(value = "计划的id") @NonNull @RequestParam(value = "pid") Long pid,
                                    @ApiParam(value = "计划参与的人数") @NonNull @RequestParam(value = "hc") Integer hc) throws IOException;

    @GetMapping(value = "/popular/avatar")
    @ApiOperation(value = "供计划相关微服务调用，更新用户头像")
    public void updateUserAvatar(@ApiParam(value = "计划的id") @NonNull @RequestParam(value = "pid") Long pid,
                                 @ApiParam(value = "用户头像url") @NonNull @RequestParam(value = "hc") String url) throws IOException;

    @GetMapping(value = "/popular/rate")
    @ApiOperation(value = "供计划微服务调用，给一个计划点赞")
    public void ratePopularPlan(@ApiParam(value = "计划的id") @NonNull @RequestParam(value = "pid") Long pid) throws IOException;

    @GetMapping(value = "/popular/cancel-rate")
    @ApiOperation(value = "供计划微服务调用，给一个计划取消点赞")
    public void cancelRatePopularPlan(@ApiParam(value = "计划的id") @NonNull @RequestParam(value = "pid") Long pid) throws IOException;

}

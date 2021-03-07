package cn.fusionfuture.bugu.search.controller;

import cn.fusionfuture.bugu.search.dto.PlanTrendDTO;
import cn.fusionfuture.bugu.search.service.IPopularTrendService;
import cn.fusionfuture.bugu.search.vo.PopularTrendVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author lzr
 * @date 2021/3/3 19:27
 */
@RestController
@RequestMapping("/trend")
@Api(tags = "查询动态的接口")
public class PopularTrendController {

    @Autowired
    private IPopularTrendService popularTrendService;

    @GetMapping(value = "/query")
    @ApiOperation(value = "按照查询条件动态")
    public PopularTrendVO queryPopularTrend(@ApiParam(value = "用户id") @RequestParam(value = "uid", defaultValue = "") Long uid,
                                           @ApiParam(value = "计划id") @RequestParam(value = "pid", defaultValue = "") Long pid,
                                           @ApiParam(value = "查询的页码") @RequestParam(value = "pn", defaultValue = "1") Integer pageNum,
                                           @ApiParam(value = "页面的大小") @RequestParam(value = "ps", defaultValue = "5") Integer pageSize) throws IOException {

        return popularTrendService.queryPopularTrend(uid, pid, pageNum, pageSize);

    }

    @GetMapping(value = "/rate")
    @ApiOperation(value = "供计划微服务调用，给一个计划点赞")
    public void ratePopularTrend(@ApiParam(value = "的id") @NonNull @RequestParam(value = "pid") Long pid) throws IOException {
        popularTrendService.ratePopularTrend(pid);
    }

    @GetMapping(value = "/cancel-rate")
    @ApiOperation(value = "供计划微服务调用，给一个计划取消点赞")
    public void cancelRatePopularPlan(@ApiParam(value = "计划的id") @NonNull @RequestParam(value = "pid") Long pid) throws IOException {
        popularTrendService.cancelRatePopularTrend(pid);
    }

    @GetMapping(value = "/rate-ac")
    @ApiOperation(value = "供计划微服务调用，给一个打卡认可")
    public void rateAgreeCount(@ApiParam(value = "的id") @NonNull @RequestParam(value = "pid") Long pid) throws IOException {
        popularTrendService.rateAgreeCount(pid);
    }

    @GetMapping(value = "/cancel-ac")
    @ApiOperation(value = "供计划微服务调用，给一个打卡取消认可")
    public void cancelAgreeCount(@ApiParam(value = "计划的id") @NonNull @RequestParam(value = "pid") Long pid) throws IOException {
        popularTrendService.cancelAgreeCount(pid);
    }

    @GetMapping(value = "/rate-dac")
    @ApiOperation(value = "供计划微服务调用，给一个打卡不认可")
    public void rateDisagreeCount(@ApiParam(value = "的id") @NonNull @RequestParam(value = "pid") Long pid) throws IOException {
        popularTrendService.rateDisagreeCount(pid);
    }

    @GetMapping(value = "/cancel-dac")
    @ApiOperation(value = "供计划微服务调用，给一个打卡取消不认可")
    public void cancelDisagreeCount(@ApiParam(value = "计划的id") @NonNull @RequestParam(value = "pid") Long pid) throws IOException {
        popularTrendService.cancelDisagreeCount(pid);
    }

    @GetMapping(value = "/rate-cq")
    @ApiOperation(value = "供计划微服务调用，给一个计划评论数")
    public void rateCommentQuantity(@ApiParam(value = "的id") @NonNull @RequestParam(value = "pid") Long pid) throws IOException {
        popularTrendService.rateCommentQuantity(pid);
    }

    @GetMapping(value = "/cancel-cq")
    @ApiOperation(value = "供计划微服务调用，给一个计划取消评论数")
    public void cancelCommentQuantity(@ApiParam(value = "计划的id") @NonNull @RequestParam(value = "pid") Long pid) throws IOException {
        popularTrendService.cancelCommentQuantity(pid);
    }

    @GetMapping(value = "/content")
    @ApiOperation(value = "供计划相关微服务调用，更新计划的内容")
    public void updatePlanStatus(@ApiParam(value = "计划的id") @NonNull @RequestParam(value = "pid") Long pid,
                                 @ApiParam(value = "计划内容") @NonNull @RequestParam(value = "ct") String content) throws IOException {

        popularTrendService.updateContent(pid, content);

    }

    @GetMapping(value = "/headcount")
    @ApiOperation(value = "供计划相关微服务调用，更新计划的参与人数")
    public void updatePlanHeadcount(@ApiParam(value = "计划的id") @NonNull @RequestParam(value = "pid") Long pid,
                                    @ApiParam(value = "计划图片") @NonNull @RequestParam(value = "imageUrls") List<String> imageUrls) throws IOException {
        popularTrendService.updateImageUrls(pid, imageUrls);

    }

    @PostMapping(value = "")
    @ApiOperation(value = "供计划微服务远程调用，创建首页信息")
    public void createPopularPlan(@ApiParam(value = "首页计划的dto") @RequestBody PlanTrendDTO planTrendDTO) throws IOException {
        popularTrendService.createPopularTrend(planTrendDTO);
    }


}

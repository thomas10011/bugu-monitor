package cn.fusionfuture.bugu.monitor.controller;


import cn.fusionfuture.bugu.monitor.dto.MonitorPlanTrendDTO;
import cn.fusionfuture.bugu.monitor.feign.SearchFeignService;
import cn.fusionfuture.bugu.monitor.feign.UserFeignService;
import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPunchRecordService;
import cn.fusionfuture.bugu.monitor.vo.BasicPunchVO;
import cn.fusionfuture.bugu.monitor.vo.MonitorPlanTrendVO;
import cn.fusionfuture.bugu.monitor.vo.SimplePunchVO;
import cn.fusionfuture.bugu.utils.oss.OssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
@RestController
@Api(tags = "用户进行打卡操作")
public class PmsMonitorPunchRecordController {

    @Autowired
    IPmsMonitorPunchRecordService monitorPunchRecordService;

    @Autowired
    UserFeignService userFeignService;

    @GetMapping("/policy")
    @ApiOperation(value = "获取上传图片需要的policy")
    public Map<String, String> getPolicy() throws IOException, ServletException {
        return OssUtil.getPolicy("/punch");
    }

    @ApiOperation(value = "用户打卡一个计划")
    @PostMapping("monitor-plan/punch")
    public String punch(@ApiParam(value = "计划的id") @RequestParam(name = "pid") Long planId,
                      @ApiParam(value = "打卡的内容") @RequestParam(name = "ct") String content,
                      @ApiParam(value = "打卡图片url列表") @RequestParam(name = "iu") List<String> imageUrls) {
        return monitorPunchRecordService.punch(planId, content, imageUrls);
    }

    @PostMapping("/punch/like")
    @ApiOperation(value = "对一条打卡记录进行点赞")
    public void like(@ApiParam(value = "打卡id") @RequestParam(name = "punchId") Long punchId){
        monitorPunchRecordService.like(punchId);
    }

    @ApiOperation(value = "根据打卡id查询打卡相关信息")
    @GetMapping(value = "/punch/detail/{punchId}")
    public BasicPunchVO queryBasicPunchVO(@Validated
                                                  @ApiParam(value = "打卡id") @PathVariable(value = "punchId") Long punchId) {
        return monitorPunchRecordService.queryBasicPunchVO(punchId);
    }

    @ApiOperation(value = "根据计划id查询打卡相关信息")
    @GetMapping(value = "plan/punch/detail/{pid}")
    public List<SimplePunchVO> querySimplePunchVO(@Validated
                                          @ApiParam(value = "计划id") @PathVariable(value = "pid") Long pid) {
        return monitorPunchRecordService.querySimplePunchVO(pid);
    }

    @ApiOperation(value = "根据用户id查询打卡相关信息")
    @GetMapping(value = "/plan/trend/{uid}")
    public List<MonitorPlanTrendVO> queryMonitorPlanTrendVO(@Validated
                                                            @ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid){

        return monitorPunchRecordService.queryMonitorPlanTrendVO(uid);
    }
}

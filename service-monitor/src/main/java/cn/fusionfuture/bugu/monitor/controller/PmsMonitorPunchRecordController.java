package cn.fusionfuture.bugu.monitor.controller;


import cn.fusionfuture.bugu.monitor.dto.PunchForMessageDTO;
import cn.fusionfuture.bugu.monitor.feign.UserFeignService;
import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPunchRecordService;
import cn.fusionfuture.bugu.monitor.vo.punch.BasicPunchVO;
import cn.fusionfuture.bugu.monitor.vo.punch.DetailedPunchVO;
import cn.fusionfuture.bugu.monitor.vo.punch.PlanTrendVO;
import cn.fusionfuture.bugu.monitor.vo.punch.SimplePunchVO;
import cn.fusionfuture.bugu.utils.oss.OssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;
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
@Api(tags = "打卡")
public class PmsMonitorPunchRecordController {

    @Autowired
    IPmsMonitorPunchRecordService monitorPunchRecordService;

    @Autowired
    UserFeignService userFeignService;

    @GetMapping("/policy")
    @ApiOperation(value = "获取上传图片需要的policy")
    public Map<String, String> getPolicy() throws IOException, ServletException {
        return OssUtil.getPolicy("punch/");
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
    public void like(@ApiParam(value = "打卡id") @RequestParam(name = "punchId") Long punchId) throws IOException {
        monitorPunchRecordService.like(punchId);
    }

    @PostMapping("/punch/cancelLike")
    @ApiOperation(value = "对一条打卡记录取消点赞")
    public void cancelLike(@ApiParam(value = "打卡id") @RequestParam(name = "punchId") Long punchId) throws IOException {
        monitorPunchRecordService.cancelLike(punchId);
    }

    @ApiOperation(value = "根据打卡id查询打卡部分信息（打卡日历下的打卡数据）")
    @GetMapping(value = "/punch/basic/{punchId}")
    public BasicPunchVO queryBasicPunchVO(@Validated
                                                  @ApiParam(value = "打卡id") @PathVariable(value = "punchId") Long punchId) {
        return monitorPunchRecordService.queryBasicPunchVO(punchId);
    }

    @ApiOperation(value = "根据打卡id查询打卡详情（打卡详情界面）")
    @GetMapping(value = "/punch/detailed/{punchId}")
    public DetailedPunchVO queryDetailedPunchVO(@Validated
                                          @ApiParam(value = "打卡id") @PathVariable(value = "punchId") Long punchId) {
        return monitorPunchRecordService.queryDetailedPunchVO(punchId);
    }

    @ApiOperation(value = "根据计划id查询打卡相关信息")
    @GetMapping(value = "plan/punch/detail/{pid}")
    public List<SimplePunchVO> querySimplePunchVO(@Validated
                                          @ApiParam(value = "计划id") @PathVariable(value = "pid") Long pid) {
        return monitorPunchRecordService.querySimplePunchVO(pid);
    }

    @ApiOperation(value = "根据用户id查询打卡相关信息")
    @GetMapping(value = "/plan/trend/{uid}")
    public List<PlanTrendVO> queryMonitorPlanTrendVO(@Validated
                                                            @ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid){

        return monitorPunchRecordService.queryMonitorPlanTrendVO(uid);
    }

    @ApiOperation(value = "根据打卡id查询打卡信息（message_service调用)")
    @GetMapping(value = "/punch/for-message/{punchId}")
    public PunchForMessageDTO queryPunchForMessageDTO(@Validated
                                                @ApiParam(value = "打卡id") @PathVariable(value = "punchId") Long punchId) {
        return monitorPunchRecordService.getPunchForMessageDTO(punchId);
    }
}

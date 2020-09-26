package cn.fusionfuture.bugu.monitor.controller;


import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPunchRecordService;
import cn.fusionfuture.bugu.utils.oss.OssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(tags = "用户进行打卡操作")
public class PmsMonitorPunchRecordController {

    @Autowired
    IPmsMonitorPunchRecordService monitorPunchRecordService;

    @GetMapping("/policy")
    @ApiOperation(value = "获取上传图片需要的policy")
    public Map<String, String> getPolicy() throws IOException, ServletException {
        return OssUtil.getPolicy("/punch");
    }

    @ApiOperation(value = "用户打卡一个计划")
    @PostMapping("monitor-plan/punch")
    public Long punch(@ApiParam(value = "计划的id") @RequestParam(name = "pid") Long planId,
                      @ApiParam(value = "用户的id") @RequestParam(name = "uid") Long userId,
                      @ApiParam(value = "打卡的内容") @RequestParam(name = "ct") String content,
                      @ApiParam(value = "打卡图片url列表") @RequestParam(name = "iu") List<String> imageUrls) {
        return monitorPunchRecordService.punch(planId, userId, content, imageUrls);
    }

}

package cn.fusionfuture.bugu.monitor.controller;


import cn.fusionfuture.bugu.monitor.service.IPmsMonitorPunchRecordService;
import cn.fusionfuture.bugu.utils.oss.OssUtil;
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
public class PmsMonitorPunchRecordController {

    @Autowired
    IPmsMonitorPunchRecordService monitorPunchRecordService;

    @GetMapping("/policy")
    public Map<String, String> getPolicy() throws IOException, ServletException {
        return OssUtil.getPolicy("/punch");
    }

    @PostMapping("/punch")
    public Long punch(@RequestParam Long planId,
                      @RequestParam Long userId,
                      @RequestParam String content,
                      @RequestParam List<String> imageUrls) {
        return monitorPunchRecordService.punch(planId, userId, content, imageUrls);
    }

}

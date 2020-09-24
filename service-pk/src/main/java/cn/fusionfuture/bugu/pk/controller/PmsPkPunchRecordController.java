package cn.fusionfuture.bugu.pk.controller;


import cn.fusionfuture.bugu.pk.service.IPmsPkPunchRecordService;
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
@RequestMapping("/pk/pms-pk-punch-record")
@Api(tags = "pk计划打卡")
public class PmsPkPunchRecordController {

    @Autowired
    IPmsPkPunchRecordService pkPunchRecordService;

    @GetMapping("/policy")
    @ApiOperation(value = "获取上传图片所需的policy")
    public Map<String, String> getPolicy() throws IOException, ServletException {
        return OssUtil.getPolicy("/punch");
    }


    @ApiOperation(value = "对pk计划进行打卡")
    @PostMapping("/punch")
    public Long punch(@ApiParam(value = "用户id") @RequestParam(name = "userId") Long userId,
                      @ApiParam(value = "计划id")@RequestParam(name = "planId") Long planId,
                      @ApiParam(value = "打卡内容")@RequestParam(name = "content") String content,
                      @ApiParam(value = "图片url")@RequestParam(name = "imageUrls") List<String> imageUrls) {
        return pkPunchRecordService.punch(userId, planId, content, imageUrls);
    }
}

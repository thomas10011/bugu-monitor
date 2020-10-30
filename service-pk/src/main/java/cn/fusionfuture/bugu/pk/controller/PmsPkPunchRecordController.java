package cn.fusionfuture.bugu.pk.controller;


import cn.fusionfuture.bugu.pk.dto.PunchForMessageDTO;
import cn.fusionfuture.bugu.pk.service.IPmsPkPunchRecordService;
import cn.fusionfuture.bugu.pk.vo.*;
import cn.fusionfuture.bugu.pk.vo.punch.DetailedPunchVO;
import cn.fusionfuture.bugu.pk.vo.punch.PlanTrendVO;
import cn.fusionfuture.bugu.pk.vo.punch.SimplePunchVO;
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
@Api(tags = "打卡记录")
public class PmsPkPunchRecordController {

    @Autowired
    IPmsPkPunchRecordService pkPunchRecordService;

    @GetMapping("/policy")
    @ApiOperation(value = "获取上传图片所需的policy")
    public Map<String, String> getPolicy() throws IOException, ServletException {
        return OssUtil.getPolicy("/punch");
    }

    @PostMapping("/punch")
    @ApiOperation(value = "对pk计划进行打卡")
    public String punch(@ApiParam(value = "用户id") @RequestParam(name = "userId") Long userId,
                      @ApiParam(value = "计划id") @RequestParam(name = "planId") Long planId,
                      @ApiParam(value = "打卡内容") @RequestParam(name = "content") String content,
                      @ApiParam(value = "图片url") @RequestParam(name = "imageUrls") List<String> imageUrls) {
        return pkPunchRecordService.punch(userId, planId, content, imageUrls);
    }

    @PostMapping("/punch/like")
    @ApiOperation(value = "对一条打卡记录进行点赞")
    public void like(@ApiParam(value = "打卡id") @RequestParam(name = "punchId") Long punchId){
        pkPunchRecordService.like(punchId);
    }

    @PostMapping("/punch/cancelLike")
    @ApiOperation(value = "对一条打卡记录取消点赞")
    public void cancleLike(@ApiParam(value = "打卡id") @RequestParam(name = "punchId") Long punchId){
        pkPunchRecordService.cancelLike(punchId);
    }

    @ApiOperation(value = "根据打卡id查询打卡相关信息(打卡日历下的打卡信息）")
    @GetMapping(value = "/punch/basic/{punchId}")
    public PunchWithImageVO queryPunchWithImageVO(@Validated
                                                      @ApiParam(value = "打卡id") @PathVariable(value = "punchId") Long punchId) {
        return pkPunchRecordService.queryPunchWithImageVO(punchId);
    }

    @ApiOperation(value = "根据打卡id查询打卡详情（打卡详情界面）")
    @GetMapping(value = "/punch/detailed/{punchId}")
    public DetailedPunchVO queryDetailedPunchVO(@Validated
                                                @ApiParam(value = "打卡id") @PathVariable(value = "punchId") Long punchId) {
        return pkPunchRecordService.queryDetailedPunchVO(punchId);
    }

    @ApiOperation(value = "根据用户id和计划id查询打卡相关信息")
    @GetMapping(value = "plan/punch/detail/{uid}&{pid}")
    public List<SimplePunchVO> querySimplePunchVO(@Validated
                                                      @ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid,
                                                  @ApiParam(value = "计划id") @PathVariable(value = "pid") Long pid) {
        return pkPunchRecordService.querySimplePunchVO(uid,pid);
    }

    @ApiOperation(value = "根据用户id查询打卡相关信息")
    @GetMapping(value = "/plan/trend/{uid}")
    public List<PlanTrendVO> querypkPlanTrendVO(@Validated
                                                            @ApiParam(value = "用户id") @PathVariable(value = "uid") Long uid){

        return pkPunchRecordService.queryPkPlanTrendVO(uid);
    }

    @ApiOperation(value = "根据打卡id查询打卡信息（message_service调用)")
    @GetMapping(value = "/punch/for-message/{punchId}")
    public PunchForMessageDTO queryPunchForMessageDTO(@Validated
                                                      @ApiParam(value = "打卡id") @PathVariable(value = "punchId") Long punchId) {
        return pkPunchRecordService.getPunchForMessageDTO(punchId);
    }

}

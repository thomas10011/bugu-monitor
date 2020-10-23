package cn.fusionfuture.bugu.pk.controller;


import cn.fusionfuture.bugu.pk.service.IPmsPkVoteRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
@RestController
@Api(tags = "投票")
public class PmsPkVoteRecordController {

    @Autowired
    IPmsPkVoteRecordService pkVoteRecordService;

    @PostMapping("/vote")
    @ApiOperation(value = "投票")
    public void vote(@ApiParam(value = "用户id") @RequestParam Long userId,
                     @ApiParam(value = "打卡id") @RequestParam Long punchId,
                     @ApiParam(value = "投票结果") @RequestParam Boolean voteResult){
        pkVoteRecordService.vote(userId, punchId, voteResult);
    }

}

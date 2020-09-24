package cn.fusionfuture.bugu.pk.controller;


import cn.fusionfuture.bugu.pk.service.IPmsPkVoteRecordService;
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
@RequestMapping("/pk/pms-pk-vote-record")
public class PmsPkVoteRecordController {

    @Autowired
    IPmsPkVoteRecordService pkVoteRecordService;

    @PostMapping("/vote")
    public void vote(@RequestParam Long userId,@RequestParam Long punchId,@RequestParam Boolean voteResult){
        pkVoteRecordService.vote(userId, punchId, voteResult);
    }

}

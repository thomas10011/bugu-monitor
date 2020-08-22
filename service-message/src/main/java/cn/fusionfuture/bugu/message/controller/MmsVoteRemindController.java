package cn.fusionfuture.bugu.message.controller;

import cn.fusionfuture.bugu.message.service.IMmsVoteRemindService;
import cn.fusionfuture.bugu.message.vo.VoteVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.entity.MmsVoteRemind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiLan
 * @version 1.0
 * @class MmsVoteRemindController
 * @description TODO
 * @date 2020/8/22 15:00
 */
@RestController
public class MmsVoteRemindController {

    @Autowired
    IMmsVoteRemindService iMmsVoteRemindService;

    /**
     * TODO 
     * @author LiLan
     * @since 2020/8/22 15:03
     * @param mmsVoteRemind
     * @return cn.fusionfuture.bugu.pojo.api.CommonResult<?>
     **/
    @PostMapping(value = "/vote-remind")
    public CommonResult<?> addVote (MmsVoteRemind mmsVoteRemind) {
        iMmsVoteRemindService.addVoteRemind(mmsVoteRemind);
        return CommonResult.success();
    }

    /**
     * TODO 
     * @author LiLan
     * @since 2020/8/22 15:03
     * @param id
     * @return java.util.List<cn.fusionfuture.bugu.message.vo.VoteVO>
     **/
    @GetMapping(value = "/vote-remind")
    public List<VoteVO> getVote(@RequestParam(name = "id") Long id){
        List<VoteVO> voteVOList = iMmsVoteRemindService.getVoteRemind(id);
        return voteVOList;
    }
}

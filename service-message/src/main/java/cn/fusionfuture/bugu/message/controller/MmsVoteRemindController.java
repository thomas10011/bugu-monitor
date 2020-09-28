package cn.fusionfuture.bugu.message.controller;

import cn.fusionfuture.bugu.message.service.IMmsVoteRemindService;
import cn.fusionfuture.bugu.message.vo.VoteVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.entity.MmsVoteRemind;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 * @description 投票提示
 * @date 2020/8/22 15:00
 */
@RestController
@Api(tags="投票提示")
public class MmsVoteRemindController {

    @Autowired
    IMmsVoteRemindService iMmsVoteRemindService;

    /**
     * 发送投票提醒
     * @author LiLan
     * @since 2020/8/22 15:03
     * @param mmsVoteRemind
     * @return cn.fusionfuture.bugu.pojo.api.CommonResult<?>
     **/
    @PostMapping(value = "/vote-remind")
    @ApiOperation(value = "发送投票提醒")
    public CommonResult<?> addVote (MmsVoteRemind mmsVoteRemind) {
        iMmsVoteRemindService.addVoteRemind(mmsVoteRemind);
        return CommonResult.success();
    }

    /**
     * 接收投票提醒
     * @author LiLan
     * @since 2020/8/22 15:03
     * @param pn 当前所在页id
     * @param ps 页面size
     * @param id 接收者的id，即当前用户id
     * @return java.util.List<cn.fusionfuture.bugu.message.vo.VoteVO>
     **/
    @GetMapping(value = "/vote-remind")
    @ApiOperation(value = "接收投票提醒")
    public PageInfo<?> getVote(@ApiParam(value = "当前所在页") @RequestParam(defaultValue = "1") Integer pn, @ApiParam(value = "页面size") @RequestParam(defaultValue = "5") Integer ps, @ApiParam(value = "接收者的id，即当前用户id") @RequestParam(name = "id") Long id){
        PageInfo<VoteVO> voteVOList = iMmsVoteRemindService.getVoteRemind(pn, ps, id);
        return voteVOList;
    }
}

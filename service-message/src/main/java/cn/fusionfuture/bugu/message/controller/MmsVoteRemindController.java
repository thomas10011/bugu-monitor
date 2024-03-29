package cn.fusionfuture.bugu.message.controller;

import cn.fusionfuture.bugu.message.feign.MonitorFeignService;
import cn.fusionfuture.bugu.message.feign.PkFeignService;
import cn.fusionfuture.bugu.message.service.IMmsRabbitMQGatewayService;
import cn.fusionfuture.bugu.message.service.IMmsVoteRemindService;
import cn.fusionfuture.bugu.message.vo.VoteVO;
import cn.fusionfuture.bugu.message.vo.input.IVoteVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.api.ResultCode;
import cn.fusionfuture.bugu.pojo.constants.VoteJudge;
import cn.fusionfuture.bugu.pojo.entity.MmsVoteRemind;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
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
    final Integer MONITOR_PLAN = 1;
    final Integer PK_PLAN = 2;
    @Autowired
    private MonitorFeignService monitorFeignService;

    @Autowired
    private PkFeignService pkFeignService;

    @Autowired
    IMmsVoteRemindService iMmsVoteRemindService;

    @Autowired
    private IMmsRabbitMQGatewayService iMmsRabbitMQGatewayService;
    /**
     * 发送投票提醒
     * @author LiLan
     * @since 2020/8/22 15:03
     * @param iVoteVO
     * @return cn.fusionfuture.bugu.pojo.api.CommonResult<?>
     **/
    @PostMapping(value = "/vote-remind")
    @ApiOperation(value = "发送投票提醒")
    public CommonResult<?> addVote (IVoteVO iVoteVO) throws JsonProcessingException {

        Integer status = 0;

        //        同步pk服务和监督服务数据
        Integer planType = iVoteVO.getPlanTypeId();
        if(planType==PK_PLAN){
          status = pkFeignService.vote(iVoteVO.getSendUserId(),iVoteVO.getPunchId(),iVoteVO.getIsApproved()).getData();
          System.out.println("status"+status);
        }
        else if(planType==MONITOR_PLAN){
          status = monitorFeignService.vote(iVoteVO.getSendUserId(),iVoteVO.getPunchId(),iVoteVO.getIsApproved()).getData();

        }
        if(status==VoteJudge.VoteSUCCESS){
            MmsVoteRemind mmsVoteRemind = new MmsVoteRemind();
            BeanUtils.copyProperties(iVoteVO,mmsVoteRemind);
            iMmsVoteRemindService.addVoteRemind(mmsVoteRemind);

            ObjectMapper mapper = new ObjectMapper();
            String messaged = mapper.writeValueAsString(mmsVoteRemind);
            messaged = messaged.substring(0,messaged.lastIndexOf("}"))+",\"messageType\":\""+iVoteVO.getMessageType()+"\"}";
            iMmsRabbitMQGatewayService.sendMessage2Mqtt(messaged, mmsVoteRemind.getReceiveUserId()+"");

            return CommonResult.success();
        }
        else if(status==VoteJudge.IsVoted){
            return CommonResult.fail(ResultCode.USER_HAS_VOTED);
        }else {
            return CommonResult.fail(ResultCode.USER_HAS_NOT_ENROLLED);
        }


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

package cn.fusionfuture.bugu.message.controller;

import cn.fusionfuture.bugu.message.feign.MonitorFeignService;
import cn.fusionfuture.bugu.message.feign.PkFeignService;
import cn.fusionfuture.bugu.message.service.IMmsLikeRemindService;
import cn.fusionfuture.bugu.message.service.IMmsPunchRemindService;
import cn.fusionfuture.bugu.message.service.IMmsRabbitMQGatewayService;
import cn.fusionfuture.bugu.message.vo.LikeVO;
import cn.fusionfuture.bugu.message.vo.input.ILikeVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.entity.MmsEnrollRemind;
import cn.fusionfuture.bugu.pojo.entity.MmsLikeRemind;
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
 * @class MmsLikeRemindController
 * @description 点赞提示
 * @date 2020/8/22 14:39
 */

@RestController
@Api(tags="点赞提示")
public class MmsLikeRemindController {
    final Integer MONITOR_PLAN = 1;
    final Integer PK_PLAN = 2;
    @Autowired
    private MonitorFeignService monitorFeignService;

    @Autowired
    private PkFeignService pkFeignService;
    @Autowired
    IMmsLikeRemindService iMmsLikeRemindService;

    @Autowired
    private IMmsRabbitMQGatewayService iMmsRabbitMQGatewayService;

    /**
     * 发送点赞消息
     * @author LiLan
     * @since 2020/8/22 15:01
     * @param iLikeVO
     * @return cn.fusionfuture.bugu.pojo.api.CommonResult<?>
     **/
    @PostMapping(value = "/like-remind")
    @ApiOperation(value = "发送点赞消息")
    public CommonResult<?> addLike (ILikeVO iLikeVO) throws JsonProcessingException {
        //        同步pk服务和监督服务数据
        Integer planType = iLikeVO.getPlanTypeId();
        if(planType==PK_PLAN){
            pkFeignService.like(iLikeVO.getPunchId());
        }
        else if(planType==MONITOR_PLAN){
            monitorFeignService.like(iLikeVO.getPunchId());
        }

        MmsLikeRemind mmsLikeRemind = new MmsLikeRemind();
        BeanUtils.copyProperties(iLikeVO,mmsLikeRemind);
        iMmsLikeRemindService.addLikeRemind(mmsLikeRemind);

        ObjectMapper mapper = new ObjectMapper();
        String messaged = mapper.writeValueAsString(mmsLikeRemind);
        messaged = messaged.substring(0,messaged.lastIndexOf("}"))+",\"messageType\":\""+iLikeVO.getMessageType()+"\"}";
        iMmsRabbitMQGatewayService.sendMessage2Mqtt(messaged, mmsLikeRemind.getReceiveUserId()+"");

        return CommonResult.success();
    }


    /**
     * 用户取消点赞
     * @author LiLan
     * @since 2020/10/28 8:41
     * @param uid, pid
     * @return java.lang.Boolean
     **/
    @PostMapping(value = "/cancel-like")
    @ApiOperation(value = "取消点赞")
    public Boolean cancelLike(@ApiParam(value = "即当前用户id") @RequestParam(name = "uid") Long uid,
                              @ApiParam(value = "打卡id") @RequestParam(name = "pid") Long pid,
                              @ApiParam(value = "打卡所属的计划类型") @RequestParam(name = "ptype") Integer planType){

        if(planType==PK_PLAN){
            pkFeignService.cancleLike(pid);
        }
        else if(planType==MONITOR_PLAN){
            monitorFeignService.cancelLike(pid);
        }
        return iMmsLikeRemindService.cancelLike(uid,pid);
    }

    /**
     * 获取所有点赞提醒
     * @author LiLan
     * @since 2020/8/22 15:01
     * @param pn 当前所在页
     * @param ps 页面sizeid
     * @param id  接收者的id，即当前用户id
     * @return java.util.List<cn.fusionfuture.bugu.message.vo.LikeVO>
     **/
    @GetMapping(value = "/like-remind")
    @ApiOperation(value = "获取点赞提醒")
    public PageInfo<?> getLike(@ApiParam(value = "当前所在页") @RequestParam(defaultValue = "1") Integer pn, @ApiParam(value = "页面size") @RequestParam(defaultValue = "5") Integer ps, @ApiParam(value = "接收者的id，即当前用户id") @RequestParam(name = "id") Long id){
        PageInfo<LikeVO> likeVOList = iMmsLikeRemindService.getLikeRemind(pn, ps, id);
        return likeVOList;
    }


    @GetMapping(value = "/is_like")
    @ApiOperation(value = "获取当前用户是否对该打卡点赞")
    public Boolean isLike(@ApiParam(value = "即当前用户id") @RequestParam(name = "uid") Long uid,@ApiParam(value = "打卡id") @RequestParam(name = "pid") Long pid){
        return iMmsLikeRemindService.isLike(uid,pid);
    }
}

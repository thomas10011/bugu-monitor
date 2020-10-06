package cn.fusionfuture.bugu.message.controller;

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
}

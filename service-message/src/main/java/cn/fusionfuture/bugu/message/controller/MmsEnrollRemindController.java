package cn.fusionfuture.bugu.message.controller;


import cn.fusionfuture.bugu.message.feign.MonitorFeignService;
import cn.fusionfuture.bugu.message.feign.PkFeignService;
import cn.fusionfuture.bugu.message.service.IMmsEnrollRemindService;
import cn.fusionfuture.bugu.message.service.IMmsRabbitMQGatewayService;
import cn.fusionfuture.bugu.message.vo.EnrollVO;
import cn.fusionfuture.bugu.message.vo.input.IEnrollVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.entity.MmsEnrollRemind;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PKCS12Attribute;
import java.util.List;

/**
 * @author LiLan
 * @version 1.0
 * @class MmsEnrollRemindController
 * @description 报名提示
 * @date 2020/8/22 14:39
 */
@RestController
@Api(tags="报名提示")
public class MmsEnrollRemindController {
    final Integer MONITOR_PLAN = 1;
    final Integer PK_PLAN = 2;

    @Autowired
    IMmsEnrollRemindService iMmsEnrollRemindService;

    @Autowired
    private IMmsRabbitMQGatewayService iMmsRabbitMQGatewayService;

    @Autowired
    private MonitorFeignService monitorFeignService;

    @Autowired
    private PkFeignService pkFeignService;

    /**
     * 发送报名信息
     * @author LiLan
     * @since 2020/8/21 17:45
     * @param iEnrollVO 报名提醒对象
     * @return cn.fusionfuture.bugu.pojo.api.CommonResult<java.lang.Object>
     **/
    @PostMapping(value = "/enroll-remind")
    @ApiOperation(value = "发送报名信息")
    public CommonResult<?> addEnroll ( IEnrollVO iEnrollVO) throws JsonProcessingException {
        MmsEnrollRemind mmsEnrollRemind = new MmsEnrollRemind();
        BeanUtils.copyProperties(iEnrollVO,mmsEnrollRemind);

        iMmsEnrollRemindService.addEnrollRemind(mmsEnrollRemind);
        Integer planType = iEnrollVO.getPlanTypeId();
        if(planType==PK_PLAN){
            pkFeignService.punch(iEnrollVO.getSendUserId(),iEnrollVO.getPlanId());
        }

        ObjectMapper mapper = new ObjectMapper();
        String messaged = mapper.writeValueAsString(mmsEnrollRemind);
        messaged = messaged.substring(0,messaged.lastIndexOf("}"))+",\"messageType\":\""+iEnrollVO.getMessageType()+"\"}";
        iMmsRabbitMQGatewayService.sendMessage2Mqtt(messaged, mmsEnrollRemind.getReceiveUserId()+"");

        return CommonResult.success();
    }

    /**
     * 获取所有报名提醒
     * @author LiLan
     * @since 2020/8/21 18:01
     * @param pn 当前所在页
     * @param ps 页面size
     * @param id  接收者的id
     * @return java.util.List<cn.fusionfuture.bugu.message.vo.EnrollVO>
     **/
    @GetMapping(value="/enroll-remind")
    @ApiOperation(value = "获取所有报名提醒")
    public PageInfo<?> getEnroll(@ApiParam(value = "当前所在页") @RequestParam(defaultValue = "1") Integer pn, @ApiParam(value = "页面size") @RequestParam(defaultValue = "5") Integer ps, @ApiParam(value = "接收者的id，即当前用户id") @RequestParam(name = "id") Long id){
        PageInfo<EnrollVO> enrollVOList = iMmsEnrollRemindService.getEnrollRemind(pn,ps,id);
        return enrollVOList;
    }

}

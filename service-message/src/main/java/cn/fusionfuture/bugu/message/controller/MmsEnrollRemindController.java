package cn.fusionfuture.bugu.message.controller;


import cn.fusionfuture.bugu.message.service.IMmsEnrollRemindService;
import cn.fusionfuture.bugu.message.vo.EnrollVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.entity.MmsEnrollRemind;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LiLan
 * @version 1.0
 * @class MmsEnrollRemindController
 * @description 报名提示
 * @date 2020/8/22 14:39
 */
@RestController
public class MmsEnrollRemindController {

    @Autowired
    IMmsEnrollRemindService iMmsEnrollRemindService;

    /**
     * 发送报名信息
     * @author LiLan
     * @since 2020/8/21 17:45
     * @param mmsEnrollRemind 报名提醒对象
     * @return cn.fusionfuture.bugu.pojo.api.CommonResult<java.lang.Object> 
     **/
    @PostMapping(value = "/enroll-remind")
    @ApiOperation(value = "发送报名信息")
    public CommonResult<?> addEnroll ( MmsEnrollRemind mmsEnrollRemind) {
        iMmsEnrollRemindService.addEnrollRemind(mmsEnrollRemind);
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
    public PageInfo<?> getEnroll(@RequestParam(defaultValue = "1") Integer pn, @RequestParam(defaultValue = "5") Integer ps,@RequestParam(value="id") Long id){
       PageInfo<EnrollVO> enrollVOList = iMmsEnrollRemindService.getEnrollRemind(pn,ps,id);
       return enrollVOList;
    }

}

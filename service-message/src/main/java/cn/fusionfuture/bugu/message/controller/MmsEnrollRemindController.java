package cn.fusionfuture.bugu.message.controller;


import cn.fusionfuture.bugu.message.service.IMmsEnrollRemindService;
import cn.fusionfuture.bugu.message.vo.EnrollVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.entity.MmsEnrollRemind;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
@RestController
public class MmsEnrollRemindController {

    @Autowired
    IMmsEnrollRemindService iMmsEnrollRemindService;

    @RequestMapping(value = "/enroll-remind", method = RequestMethod.POST)
    public CommonResult<Object> addEnroll ( MmsEnrollRemind mmsEnrollRemind) {
        System.out.println("执行");
        iMmsEnrollRemindService.addEnrollRemind(mmsEnrollRemind);
        return CommonResult.success();
    }

    @RequestMapping(value="/enroll-remind",method = RequestMethod.GET)
    public CommonResult getEnroll(@RequestParam(value="id") Long id){
       List<EnrollVO> enrollVOList = iMmsEnrollRemindService.getEnrollRemind(id);
       return CommonResult.success().append(enrollVOList);
    }




}

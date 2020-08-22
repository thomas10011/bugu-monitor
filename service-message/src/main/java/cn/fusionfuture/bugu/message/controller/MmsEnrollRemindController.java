package cn.fusionfuture.bugu.message.controller;


import cn.fusionfuture.bugu.message.service.IMmsEnrollRemindService;
import cn.fusionfuture.bugu.message.vo.EnrollVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.entity.MmsEnrollRemind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * TODO 
     * @author LiLan
     * @since 2020/8/21 17:45
     * @param mmsEnrollRemind
     * @return cn.fusionfuture.bugu.pojo.api.CommonResult<java.lang.Object> 
     **/
    @PostMapping(value = "/enroll-remind")
    public CommonResult<Object> addEnroll ( MmsEnrollRemind mmsEnrollRemind) {
        System.out.println("执行");
        iMmsEnrollRemindService.addEnrollRemind(mmsEnrollRemind);
        return CommonResult.success();
    }

    /**
     * TODO
     * @author LiLan
     * @since 2020/8/21 18:01
     * @param id
     * @return java.util.List<cn.fusionfuture.bugu.message.vo.EnrollVO>
     **/
    @GetMapping(value="/enroll-remind")
    public List<EnrollVO> getEnroll(@RequestParam(value="id") Long id){
       List<EnrollVO> enrollVOList = iMmsEnrollRemindService.getEnrollRemind(id);
       return enrollVOList;
    }

}

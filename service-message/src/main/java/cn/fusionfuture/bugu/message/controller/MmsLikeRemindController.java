package cn.fusionfuture.bugu.message.controller;

import cn.fusionfuture.bugu.message.service.IMmsLikeRemindService;
import cn.fusionfuture.bugu.message.service.IMmsPunchRemindService;
import cn.fusionfuture.bugu.message.vo.LikeVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.entity.MmsEnrollRemind;
import cn.fusionfuture.bugu.pojo.entity.MmsLikeRemind;
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
 * @description TODO
 * @date 2020/8/22 14:39
 */

@RestController
public class MmsLikeRemindController {

    @Autowired
    IMmsLikeRemindService iMmsLikeRemindService;

    /**
     * TODO
     * @author LiLan
     * @since 2020/8/22 15:01
     * @param mmsLikeRemind
     * @return cn.fusionfuture.bugu.pojo.api.CommonResult<?>
     **/
    @PostMapping(value = "/like-remind")
    public CommonResult<?> addLike (MmsLikeRemind mmsLikeRemind) {
        iMmsLikeRemindService.addLikeRemind(mmsLikeRemind);
        return CommonResult.success();
    }

    /**
     * TODO
     * @author LiLan
     * @since 2020/8/22 15:01
     * @param id
     * @return java.util.List<cn.fusionfuture.bugu.message.vo.LikeVO>
     **/
    @GetMapping(value = "/like-remind")
    public List<LikeVO> getLike(@RequestParam(name = "id") Long id){
        List<LikeVO> likeVOList = iMmsLikeRemindService.getLikeRemind(id);
        return likeVOList;
    }
}

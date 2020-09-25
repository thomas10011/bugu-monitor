package cn.fusionfuture.bugu.message.controller;

import cn.fusionfuture.bugu.message.service.IMmsLikeRemindService;
import cn.fusionfuture.bugu.message.service.IMmsPunchRemindService;
import cn.fusionfuture.bugu.message.vo.LikeVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.entity.MmsEnrollRemind;
import cn.fusionfuture.bugu.pojo.entity.MmsLikeRemind;
import com.github.pagehelper.PageInfo;
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
public class MmsLikeRemindController {

    @Autowired
    IMmsLikeRemindService iMmsLikeRemindService;

    /**
     * TODO 发送点赞消息
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
     * TODO 获取所有点赞提醒
     * @author LiLan
     * @since 2020/8/22 15:01
     * @param pn 当前所在页
     * @param ps 页面sizeid
     * @param   接收者的id，即当前用户id
     * @return java.util.List<cn.fusionfuture.bugu.message.vo.LikeVO>
     **/
    @GetMapping(value = "/like-remind")
    public PageInfo<?> getLike(@RequestParam(defaultValue = "1") Integer pn, @RequestParam(defaultValue = "5") Integer ps,@RequestParam(name = "id") Long id){
        PageInfo<LikeVO> likeVOList = iMmsLikeRemindService.getLikeRemind(pn, ps, id);
        return likeVOList;
    }
}

package cn.fusionfuture.bugu.message.controller;


import cn.fusionfuture.bugu.message.service.IMmsCommentRemindService;
import cn.fusionfuture.bugu.message.vo.CommentVO;
import cn.fusionfuture.bugu.message.vo.PunchCommentVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.entity.MmsCommentRemind;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LiLan
 * @version 1.0
 * @class MmsCommentRemindController
 * @description 评论提示
 * @date 2020/8/22 14:39
 */
@RestController
public class MmsCommentRemindController {

    @Autowired
    IMmsCommentRemindService iMmsCommentRemindService;

    /**
     * TODO 
     * @author LiLan
     * @since 2020/8/22 17:02
     * @param mmsCommentRemind 评论对象
     * @return cn.fusionfuture.bugu.pojo.api.CommonResult<?> 
     **/
    @PostMapping(value = "/comment-remind")
    public CommonResult<?> addComment(MmsCommentRemind mmsCommentRemind){
        iMmsCommentRemindService.addComment(mmsCommentRemind);
        return CommonResult.success();
    }

    /**
     * TODO 获取评论通知
     * @author LiLan
     * @since 2020/9/25 13:13
     * @param pn 当前所在页
     * @param ps 页面size
     * @param id 接收者的id
     * @return com.github.pagehelper.PageInfo<?> 
     **/
    @GetMapping(value = "/comment-remind")
    public PageInfo<?> getCommentRemind(@RequestParam(defaultValue = "1") Integer pn, @RequestParam(defaultValue = "5") Integer ps,@RequestParam(name = "id") Long id){
        PageInfo<CommentVO> commentVOList = iMmsCommentRemindService.getCommentRemind(pn,ps,id);
        return commentVOList;
    }


    /**
     * TODO 获取一个打卡下的全部评论
     * @author LiLan
     * @since 2020/8/22 17:03
     * @param id 打卡id
     * @return java.util.List<cn.fusionfuture.bugu.message.vo.PunchCommentVO>
     **/
    @GetMapping(value = "/comment")
    public List<PunchCommentVO> getPunchComment(@RequestParam(name = "punch-id") Long id){
        List<PunchCommentVO> punchCommentVOList = iMmsCommentRemindService.getPunchComment(id);
        return punchCommentVOList;
    }



}

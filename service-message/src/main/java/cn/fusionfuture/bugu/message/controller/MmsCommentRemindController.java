package cn.fusionfuture.bugu.message.controller;


import cn.fusionfuture.bugu.message.service.IMmsCommentRemindService;
import cn.fusionfuture.bugu.message.vo.CommentVO;
import cn.fusionfuture.bugu.message.vo.PunchCommentVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.entity.MmsCommentRemind;
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
public class MmsCommentRemindController {

    @Autowired
    IMmsCommentRemindService iMmsCommentRemindService;

    /**
     * TODO 
     * @author LiLan
     * @since 2020/8/22 17:02
     * @param mmsCommentRemind 
     * @return cn.fusionfuture.bugu.pojo.api.CommonResult<?> 
     **/
    @PostMapping(value = "/comment-remind")
    public CommonResult<?> addComment(MmsCommentRemind mmsCommentRemind){
        iMmsCommentRemindService.addComment(mmsCommentRemind);
        return CommonResult.success();
    }

    /**
     * TODO
     * @author LiLan
     * @since 2020/8/22 17:02
     * @param id
     * @return java.util.List<cn.fusionfuture.bugu.message.vo.CommentVO> 
     **/
    @GetMapping(value = "/comment-remind")
    public List<CommentVO> getCommentRemind(@RequestParam(name = "user-id") Long id){
        List<CommentVO> commentVOList = iMmsCommentRemindService.getCommentRemind(id);
        return commentVOList;
    }


    /**
     * TODO
     * @author LiLan
     * @since 2020/8/22 17:03
     * @param id
     * @return java.util.List<cn.fusionfuture.bugu.message.vo.PunchCommentVO>
     **/
    @GetMapping(value = "/comment")
    public List<PunchCommentVO> getPunchComment(@RequestParam(name = "punch-id") Long id){
        List<PunchCommentVO> punchCommentVOList = iMmsCommentRemindService.getPunchComment(id);
        return punchCommentVOList;
    }



}

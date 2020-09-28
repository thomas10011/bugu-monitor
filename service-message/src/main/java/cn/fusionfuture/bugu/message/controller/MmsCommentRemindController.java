package cn.fusionfuture.bugu.message.controller;


import cn.fusionfuture.bugu.message.service.IMmsCommentRemindService;
import cn.fusionfuture.bugu.message.vo.CommentVO;
import cn.fusionfuture.bugu.message.vo.PunchCommentVO;
import cn.fusionfuture.bugu.pojo.api.CommonResult;
import cn.fusionfuture.bugu.pojo.entity.MmsCommentRemind;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags="评论提示")
public class MmsCommentRemindController {

    @Autowired
    IMmsCommentRemindService iMmsCommentRemindService;

    /**
     *
     * @author LiLan
     * @since 2020/8/22 17:02
     * @param mmsCommentRemind 评论对象
     * @return cn.fusionfuture.bugu.pojo.api.CommonResult<?>
     **/
    @PostMapping(value = "/comment-remind")
    @ApiOperation(value = "发送评论")
    public CommonResult<?> addComment(MmsCommentRemind mmsCommentRemind){
        iMmsCommentRemindService.addComment(mmsCommentRemind);
        return CommonResult.success();
    }

    /**
     * 获取评论通知
     * @author LiLan
     * @since 2020/9/25 13:13
     * @param pn 当前所在页
     * @param ps 页面size
     * @param id 接收者的id
     * @return com.github.pagehelper.PageInfo<?>
     **/
    @GetMapping(value = "/comment-remind")
    @ApiOperation(value = "获取评论通知")
    public PageInfo<?> getCommentRemind(@ApiParam(value = "当前所在页") @RequestParam(defaultValue = "1") Integer pn,@ApiParam(value = "页面size") @RequestParam(defaultValue = "5") Integer ps,@ApiParam(value = "接收者的id，即当前用户id") @RequestParam(name = "id") Long id){
        PageInfo<CommentVO> commentVOList = iMmsCommentRemindService.getCommentRemind(pn,ps,id);
        return commentVOList;
    }


    /**
     * 获取一个打卡下的全部评论
     * @author LiLan
     * @since 2020/8/22 17:03
     * @param id 打卡id
     * @return java.util.List<cn.fusionfuture.bugu.message.vo.PunchCommentVO>
     **/
    @GetMapping(value = "/comment")
    @ApiOperation(value = "获取一个打卡下的全部评论")
    public List<PunchCommentVO> getPunchComment(@ApiParam(value = "打卡id") @RequestParam(name = "id") Long id){
        List<PunchCommentVO> punchCommentVOList = iMmsCommentRemindService.getPunchComment(id);
        return punchCommentVOList;
    }



}

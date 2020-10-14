package cn.fusionfuture.bugu.message.service.impl;

import cn.fusionfuture.bugu.message.mapper.*;
import cn.fusionfuture.bugu.message.service.IMmsMessageQuantityService;
import cn.fusionfuture.bugu.pojo.entity.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @version 1.0
 * @class MmsMessageQuantityServiceImpl
 * @description TODO
 * @date 2020/10/10 11:07
 */
@Service
public class MmsMessageQuantityServiceImpl implements IMmsMessageQuantityService {

    @Autowired
    MmsCommentRemindMapper mmsCommentRemindMapper;

    @Autowired
    MmsLikeRemindMapper mmsLikeRemindMapper;

    @Autowired
    MmsPunchRemindMapper mmsPunchRemindMapper;

    @Autowired
    MmsVoteRemindMapper mmsVoteRemindMapper;

    @Autowired
    MmsEnrollRemindMapper mmsEnrollRemindMapper;

    @Autowired
    MmsSystemUserMapper mmsSystemUserMapper;

    @Override
    public Map<String, Integer> GetMessageQuantity(Long userId) {
        Map<String, Integer> messageQuantity = new HashMap<>();
//        评论总数
        QueryWrapper<MmsCommentRemind> queryWrapperComment = new QueryWrapper<>();
        queryWrapperComment.eq("receive_user_id",userId);
        queryWrapperComment.eq("is_checked",false);
        List<MmsCommentRemind> mmsCommentRemindList = mmsCommentRemindMapper.selectList(queryWrapperComment);
        int commentQuantity = mmsCommentRemindList.size();
        messageQuantity.put("commentQuantity",commentQuantity);
//      点赞总数
        QueryWrapper<MmsLikeRemind> queryWrapperLike = new QueryWrapper<>();
        queryWrapperLike.eq("receive_user_id",userId);
        queryWrapperLike.eq("is_checked",false);
        List<MmsLikeRemind> mmsLikeRemindList = mmsLikeRemindMapper.selectList(queryWrapperLike);
        int likeQuantity = mmsLikeRemindList.size();
        messageQuantity.put("likeQuantity",likeQuantity);
//      打卡提示数
        QueryWrapper<MmsPunchRemind> queryWrapperPunch = new QueryWrapper<>();
        queryWrapperPunch.eq("receive_user_id",userId);
        queryWrapperPunch.eq("is_checked",false);
        List<MmsPunchRemind> mmsPunchRemindList = mmsPunchRemindMapper.selectList(queryWrapperPunch);
        int punchQuantity = mmsPunchRemindList.size();
        messageQuantity.put("punchQuantity",punchQuantity);
//      投票提示数
        QueryWrapper<MmsVoteRemind> queryWrapperVote = new QueryWrapper<>();
        queryWrapperVote.eq("receive_user_id",userId);
        queryWrapperVote.eq("is_checked",false);
        List<MmsVoteRemind> mmsVoteRemindList = mmsVoteRemindMapper.selectList(queryWrapperVote);
        int voteQuantity = mmsVoteRemindList.size();
        messageQuantity.put("voteQuantity",voteQuantity);
        //      报名提示数
        QueryWrapper<MmsEnrollRemind> queryWrapperEnroll = new QueryWrapper<>();
        queryWrapperEnroll.eq("receive_user_id",userId);
        queryWrapperEnroll.eq("is_checked",false);
        List<MmsEnrollRemind> mmsEnrollRemindList = mmsEnrollRemindMapper.selectList(queryWrapperEnroll);
        int enrollQuantity = mmsEnrollRemindList.size();
        messageQuantity.put("enrollQuantity",enrollQuantity);
//        系统消息数

        QueryWrapper<MmsSystemUser> queryWrapperSystem = new QueryWrapper<>();
        queryWrapperSystem.eq("receive_user_id",userId);
        queryWrapperSystem.eq("is_checked",false);
        List<MmsSystemUser> mmsSystemUserList = mmsSystemUserMapper.selectList(queryWrapperSystem);
        int systemMessageQuantity = mmsSystemUserList.size();
        messageQuantity.put("systemMessageQuantity",systemMessageQuantity);

        messageQuantity.put("totalRemindQuantity",punchQuantity+voteQuantity+enrollQuantity+systemMessageQuantity);

        return messageQuantity;
    }
}

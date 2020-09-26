package cn.fusionfuture.bugu.message.service.impl;

import cn.fusionfuture.bugu.message.mapper.MmsCommentRemindMapper;
import cn.fusionfuture.bugu.message.service.IMmsCommentRemindService;
import cn.fusionfuture.bugu.message.vo.CommentVO;
import cn.fusionfuture.bugu.message.vo.PunchCommentVO;
import cn.fusionfuture.bugu.pojo.entity.MmsCommentRemind;
import cn.fusionfuture.bugu.pojo.entity.MmsCommentRemind;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiLan
 * @version 1.0
 * @class MmsCommentRemindServiceImpl
 * @description TODO
 * @date 2020/8/22 15:44
 */
@Service
public class MmsCommentRemindServiceImpl extends ServiceImpl<MmsCommentRemindMapper, MmsCommentRemind> implements IMmsCommentRemindService {
    @Autowired
    private MmsCommentRemindMapper mmsCommentRemindMapper;

    @Override
    public void addComment(MmsCommentRemind mmsCommentRemind) {
        mmsCommentRemindMapper.insert(mmsCommentRemind);
    }

    @Override
    public List<CommentVO> getCommentRemind(Long id) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("receive_user_id", id);
        List<MmsCommentRemind> mmsCommentRemindList = mmsCommentRemindMapper.selectByMap(columnMap);
        System.out.println("查询出数据");
        List<CommentVO> commentVOList = new ArrayList<>();
        for (MmsCommentRemind mmsCommentRemind : mmsCommentRemindList) {
            CommentVO commentVO = new CommentVO();
            commentVO.setId(mmsCommentRemind.getId());
            commentVO.setSendTime(mmsCommentRemind.getCreateTime());
            commentVO.setSendUserId(mmsCommentRemind.getSendUserId());
            commentVO.setReceiveUserId(mmsCommentRemind.getReceiveUserId());
            commentVO.setPunchId(mmsCommentRemind.getPunchId());
            commentVO.setPlanTypeId(mmsCommentRemind.getPlanTypeId());
            commentVO.setIsChecked(mmsCommentRemind.getIsChecked());
            commentVO.setIsHidden(mmsCommentRemind.getIsHidden());
            commentVO.setCommentContent(mmsCommentRemind.getCommentContent());
            commentVO.setParentId(mmsCommentRemind.getParentId());

//           通过parentId获取父评论相关内容
            Long parentId = mmsCommentRemind.getParentId();

            MmsCommentRemind mmsCommentRemindParent = mmsCommentRemindMapper.selectById(parentId);
            commentVO.setParentConnent(mmsCommentRemindParent.getCommentContent());
//            commentVO.setParentUserId(mmsCommentRemindParent.getSendUserId());发出父评论的用户就是当前用户，即ReceiveUserId
            //          TODO:调用其他微服务获取完整数据

            commentVOList.add(commentVO);
        }
        return commentVOList;
    }

    @Override
    public List<PunchCommentVO> getPunchComment(Long id) {

        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("punch_id", id);
        List<MmsCommentRemind> mmsCommentRemindList = mmsCommentRemindMapper.selectByMap(columnMap);
        List<PunchCommentVO> commentVOList = new ArrayList<>();
        for (MmsCommentRemind mmsCommentRemind : mmsCommentRemindList) {
            PunchCommentVO commentVO = new PunchCommentVO();
            commentVO.setId(mmsCommentRemind.getId());
            commentVO.setSendTime(mmsCommentRemind.getCreateTime());
            commentVO.setSendUserId(mmsCommentRemind.getSendUserId());
            commentVO.setReceiveUserId(mmsCommentRemind.getReceiveUserId());
            commentVO.setPunchId(mmsCommentRemind.getPunchId());
            commentVO.setPlanTypeId(mmsCommentRemind.getPlanTypeId());
            commentVO.setIsChecked(mmsCommentRemind.getIsChecked());
            commentVO.setIsHidden(mmsCommentRemind.getIsHidden());
            commentVO.setCommentContent(mmsCommentRemind.getCommentContent());
            commentVO.setParentId(mmsCommentRemind.getParentId());

            //          TODO:调用其他微服务获取完整数据

            commentVOList.add(commentVO);
        }
        return commentVOList;
    }
}

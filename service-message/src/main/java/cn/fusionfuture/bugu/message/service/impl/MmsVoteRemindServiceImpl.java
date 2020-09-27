package cn.fusionfuture.bugu.message.service.impl;

import cn.fusionfuture.bugu.message.vo.VoteVO;
import cn.fusionfuture.bugu.message.vo.VoteVO;
import cn.fusionfuture.bugu.pojo.entity.MmsVoteRemind;
import cn.fusionfuture.bugu.pojo.entity.MmsVoteRemind;
import cn.fusionfuture.bugu.message.mapper.MmsVoteRemindMapper;
import cn.fusionfuture.bugu.message.service.IMmsVoteRemindService;
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
 * @class MmsVoteRemindServiceImpl
 * @description TODO
 * @date 2020/8/22 14:56
 */
@Service
public class MmsVoteRemindServiceImpl extends ServiceImpl<MmsVoteRemindMapper, MmsVoteRemind> implements IMmsVoteRemindService {
    @Autowired
    MmsVoteRemindMapper mmsVoteRemindMapper;


    @Override
    public void addVoteRemind(MmsVoteRemind mmsVoteRemind) {
        mmsVoteRemindMapper.insert(mmsVoteRemind);
    }

    @Override
    public List<VoteVO> getVoteRemind(Long id) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("receive_user_id", id);
        List<MmsVoteRemind> mmsVoteRemindList = mmsVoteRemindMapper.selectByMap(columnMap);
        System.out.println("查询出数据");
        List<VoteVO> voteVOList = new ArrayList<>();
        for (MmsVoteRemind mmsVoteRemind : mmsVoteRemindList) {
            VoteVO voteVO = new VoteVO();
            voteVO.setId(mmsVoteRemind.getId());
            voteVO.setSendTime(mmsVoteRemind.getCreateTime());
            voteVO.setSendUserId(mmsVoteRemind.getSendUserId());
            voteVO.setReceiveUserId(mmsVoteRemind.getReceiveUserId());
            voteVO.setPunchId(mmsVoteRemind.getPunchId());
            voteVO.setPlanTypeId(mmsVoteRemind.getPlanTypeId());
            voteVO.setVoteType(mmsVoteRemind.getIsApproved());
            voteVO.setIsChecked(mmsVoteRemind.getIsChecked());
            voteVO.setIsHidden(mmsVoteRemind.getIsHidden());
            //          TODO:调用其他微服务获取完整数据

            voteVOList.add(voteVO);
        }
        return voteVOList;
    }
}

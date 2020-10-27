package cn.fusionfuture.bugu.pk.service.impl;

import cn.fusionfuture.bugu.pk.mapper.PmsPkPunchRecordMapper;
import cn.fusionfuture.bugu.pk.mapper.PmsPkUserGrabTicketMapper;
import cn.fusionfuture.bugu.pojo.constants.VoteJudge;
import cn.fusionfuture.bugu.pojo.entity.PmsPkPunchRecord;
import cn.fusionfuture.bugu.pojo.entity.PmsPkUserGrabTicket;
import cn.fusionfuture.bugu.pojo.entity.PmsPkVoteRecord;
import cn.fusionfuture.bugu.pk.mapper.PmsPkVoteRecordMapper;
import cn.fusionfuture.bugu.pk.service.IPmsPkVoteRecordService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
@Service
public class PmsPkVoteRecordServiceImpl extends ServiceImpl<PmsPkVoteRecordMapper, PmsPkVoteRecord> implements IPmsPkVoteRecordService {

    @Autowired
    PmsPkVoteRecordMapper pkVoteRecordMapper;

    @Autowired
    PmsPkPunchRecordMapper pkPunchRecordMapper;

    @Autowired
    PmsPkUserGrabTicketMapper pkUserGrabTicketMapper;

    @Override
    public Integer vote(Long userId, Long punchId, Boolean voteResult){

        //在打卡之前先需要先判断用户是否已经对该打卡对应的计划抢票
        QueryWrapper<PmsPkUserGrabTicket> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("user_id",userId).eq("pk_plan_id",pkPunchRecordMapper.selectById(punchId).getPkPlanId());
        PmsPkUserGrabTicket pkUserGrabTicketDemo=pkUserGrabTicketMapper.selectOne(queryWrapper1);
        if(pkUserGrabTicketDemo==null){
            return VoteJudge.NotEnrolled;
        }
        QueryWrapper<PmsPkVoteRecord> queryWrapper2=new QueryWrapper<>();
        queryWrapper2.eq("vote_user_id",userId).eq("punch_id",punchId);
        PmsPkVoteRecord pkVoteRecordDemo=pkVoteRecordMapper.selectOne(queryWrapper2);
        if(pkVoteRecordDemo!=null){
            return VoteJudge.IsVoted;
        }
        //保存用户投票记录至投票记录表
        PmsPkVoteRecord pkVoteRecord=new PmsPkVoteRecord();
        pkVoteRecord.setVoteUserId(userId);
        pkVoteRecord.setPunchId(punchId);
        pkVoteRecord.setVoteResult(voteResult);
        pkVoteRecordMapper.insert(pkVoteRecord);

        //更新打卡记录表中的投票数（赞同数或否认数）
        if(voteResult){
            PmsPkPunchRecord pkPunchRecord=pkPunchRecordMapper.selectById(punchId);
            pkPunchRecord.setAgreeCount(pkPunchRecord.getAgreeCount()+1);
            pkPunchRecordMapper.updateById(pkPunchRecord);
        }
        else{
            PmsPkPunchRecord pkPunchRecord=pkPunchRecordMapper.selectById(punchId);
            pkPunchRecord.setDisagreeCount(pkPunchRecord.getDisagreeCount()+1);
            pkPunchRecordMapper.updateById(pkPunchRecord);
        }
        return VoteJudge.VoteSUCCESS;
    }
}

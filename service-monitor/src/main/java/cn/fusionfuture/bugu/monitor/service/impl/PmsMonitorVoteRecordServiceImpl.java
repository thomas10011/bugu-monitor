package cn.fusionfuture.bugu.monitor.service.impl;

import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPunchRecordMapper;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorUserGrabTicketMapper;
import cn.fusionfuture.bugu.pojo.constants.VoteJudge;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPunchRecord;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorUserGrabTicket;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorVoteRecord;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorVoteRecordMapper;
import cn.fusionfuture.bugu.monitor.service.IPmsMonitorVoteRecordService;
import cn.fusionfuture.bugu.pojo.entity.PmsPkUserGrabTicket;
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
public class PmsMonitorVoteRecordServiceImpl extends ServiceImpl<PmsMonitorVoteRecordMapper, PmsMonitorVoteRecord> implements IPmsMonitorVoteRecordService {

    @Autowired
    PmsMonitorVoteRecordMapper monitorVoteRecordMapper;

    @Autowired
    PmsMonitorPunchRecordMapper monitorPunchRecordMapper;

    @Autowired
    PmsMonitorUserGrabTicketMapper monitorUserGrabTicketMapper;

    @Override
    public Integer vote(Long userId, Long punchId, Boolean voteResult){


        //在打卡之前先需要先判断用户是否已经对该打卡对应的计划抢票
        QueryWrapper<PmsMonitorUserGrabTicket> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("user_id",userId).eq("monitor_plan_id",monitorPunchRecordMapper.selectById(punchId).getMonitorPlanId());
        PmsMonitorUserGrabTicket monitorUserGrabTicketDemo=monitorUserGrabTicketMapper.selectOne(queryWrapper1);
        if(monitorUserGrabTicketDemo==null){
            return VoteJudge.NotEnrolled;
        }
        //判断用户对该计划是否已打卡
        QueryWrapper<PmsMonitorVoteRecord> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("vote_user_id",userId).eq("punch_id",punchId);
        PmsMonitorVoteRecord monitorVoteRecordDemo=monitorVoteRecordMapper.selectOne(queryWrapper);
        if(monitorVoteRecordDemo!=null){
            return VoteJudge.IsVoted;
        }

        //保存用户投票记录至投票记录表
        PmsMonitorVoteRecord monitorVoteRecord=new PmsMonitorVoteRecord();
        monitorVoteRecord.setVoteUserId(userId);
        monitorVoteRecord.setPunchId(punchId);
        monitorVoteRecord.setVoteResult(voteResult);
        monitorVoteRecordMapper.insert(monitorVoteRecord);

        //更新打卡记录表中的投票数（赞同数或否认数）
        if(voteResult){
            PmsMonitorPunchRecord monitorPunchRecord = monitorPunchRecordMapper.selectById(punchId);
            monitorPunchRecordMapper.updateById(
                    new PmsMonitorPunchRecord()
                            .setId(monitorPunchRecord.getId())
                            .setAgreeCount(monitorPunchRecord.getAgreeCount() + 1)
            );
        }
        else{
            PmsMonitorPunchRecord monitorPunchRecord=monitorPunchRecordMapper.selectById(punchId);
            monitorPunchRecordMapper.updateById(
                    new PmsMonitorPunchRecord()
                            .setId(monitorPunchRecord.getId())
                            .setDisagreeCount(monitorPunchRecord.getDisagreeCount() + 1)
            );
        }
        return VoteJudge.VoteSUCCESS;
    }
}

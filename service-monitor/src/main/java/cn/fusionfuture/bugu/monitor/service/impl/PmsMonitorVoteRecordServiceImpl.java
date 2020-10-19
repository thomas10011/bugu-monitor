package cn.fusionfuture.bugu.monitor.service.impl;

import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorPunchRecordMapper;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorPunchRecord;
import cn.fusionfuture.bugu.pojo.entity.PmsMonitorVoteRecord;
import cn.fusionfuture.bugu.monitor.mapper.PmsMonitorVoteRecordMapper;
import cn.fusionfuture.bugu.monitor.service.IPmsMonitorVoteRecordService;
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

    @Override
    public void vote(Long userId, Long punchId, Boolean voteResult){

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


    }
}

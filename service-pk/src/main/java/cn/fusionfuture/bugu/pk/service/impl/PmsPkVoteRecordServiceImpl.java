package cn.fusionfuture.bugu.pk.service.impl;

import cn.fusionfuture.bugu.pojo.entity.PmsPkVoteRecord;
import cn.fusionfuture.bugu.pk.mapper.PmsPkVoteRecordMapper;
import cn.fusionfuture.bugu.pk.service.IPmsPkVoteRecordService;
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

    @Override
    public void vote(Long userId, Long punchId, Boolean voteResult){

        //保存用户投票记录
        PmsPkVoteRecord pkVoteRecord=new PmsPkVoteRecord();
        pkVoteRecord.setVoteUserId(userId);
        pkVoteRecord.setPunchId(punchId);
        pkVoteRecord.setVoteResult(voteResult);
        pkVoteRecordMapper.insert(pkVoteRecord);

    }
}

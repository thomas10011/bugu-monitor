package cn.fusionfuture.bugu.pk.service;

import cn.fusionfuture.bugu.pojo.entity.PmsPkVoteRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
public interface IPmsPkVoteRecordService extends IService<PmsPkVoteRecord> {

    /*
     * 保存用户投票的记录
     * @author zws
     * @since 2020/9/23 15:12
     * @param [userId, planId, punchId, voteResult]
     * @return void
     **/
    Integer vote(Long userId, Long punchId, Boolean voteResult);


}

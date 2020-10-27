package cn.fusionfuture.bugu.monitor.service;

import cn.fusionfuture.bugu.pojo.entity.PmsMonitorVoteRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
public interface IPmsMonitorVoteRecordService extends IService<PmsMonitorVoteRecord> {

    /*
     * 添加用户投票记录
     * @author zws
     * @since 2020/9/26 15:41
     * @param [userId, punchId, voteResult] 
     * @return void 
     **/
    public Integer vote(Long userId, Long punchId, Boolean voteResult);
}

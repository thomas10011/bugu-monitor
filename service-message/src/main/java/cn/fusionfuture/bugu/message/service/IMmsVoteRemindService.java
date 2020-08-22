package cn.fusionfuture.bugu.message.service;

import cn.fusionfuture.bugu.message.vo.VoteVO;
import cn.fusionfuture.bugu.pojo.entity.MmsVoteRemind;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author thomas
 * @since 2020-08-17
 */
public interface IMmsVoteRemindService extends IService<MmsVoteRemind> {

    void addVoteRemind(MmsVoteRemind mmsVoteRemind);

    List<VoteVO> getVoteRemind(Long id);

}

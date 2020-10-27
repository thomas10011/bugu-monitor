package cn.fusionfuture.bugu.pk.mapper;

import cn.fusionfuture.bugu.pk.vo.plan.BasicPkPlanVO;
import cn.fusionfuture.bugu.pojo.entity.PmsPkUserGrabTicket;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author thomas
 * @since 2020-08-24
 */
public interface PmsPkUserGrabTicketMapper extends BaseMapper<PmsPkUserGrabTicket> {

    /*
     * @author zws
     * @description 根据用户id查询用户参与投票的pk计划
     * @create 2020/10/25 13:39
     * @update 2020/10/25 13:39
     * @param [uid]
     * @return java.util.List<cn.fusionfuture.bugu.pk.vo.plan.BasicPkPlanVO>
     **/
    List<BasicPkPlanVO> queryUserVotePlanByUserId(Long uid);
}
